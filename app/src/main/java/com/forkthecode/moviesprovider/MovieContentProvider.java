package com.forkthecode.moviesprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MovieContentProvider extends ContentProvider {

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_MOVIES = 1;
    private static final int CODE_MOVIE = 2;
    private static final int CODE_REVIEWS = 10;

    static {
        matcher.addURI(Contract.AUTHORITY,Contract.PATH_MOVIES,CODE_MOVIES);
        matcher.addURI(Contract.AUTHORITY,Contract.PATH_MOVIES + "/#",CODE_MOVIE);
        matcher.addURI(Contract.AUTHORITY,Contract.PATH_REVIEWS,CODE_REVIEWS);
    }

    private static MovieOpenHelper openHelper;

    public MovieContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case CODE_MOVIE:

                return Contract.Movie.CONTENT_ITEM_TYPE;
            case CODE_MOVIES:
                return Contract.Movie.CONTENT_TYPE;
            case CODE_REVIEWS:
                return Contract.Review.CONTENT_TYPE;
            default: return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       Uri output = null;
        switch (matcher.match(uri)){
            case CODE_MOVIES:
                long mId = openHelper.getWritableDatabase().insert(Contract.Movie.TABLE_NAME,null,values);
                output = Contract.Movie.buildUriForId(mId);

                break;
            case CODE_REVIEWS:
                long rId = openHelper.getWritableDatabase().insert(Contract.Review.TABLE_NAME,null,values);
                output = Contract.Review.buildUriForId(rId);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return output;
    }

    @Override
    public boolean onCreate() {
        openHelper = MovieOpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor output = null;
        switch (matcher.match(uri)){

            case CODE_MOVIE:

                String id = uri.getLastPathSegment();
                output = openHelper.getReadableDatabase().query(Contract.Movie.TABLE_NAME,projection,
                        Contract.Movie._ID + " = ?",new String[]{id},null,null,sortOrder);
                break;
            case CODE_MOVIES:
                output = openHelper.getReadableDatabase().query(Contract.Movie.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CODE_REVIEWS:
                output = openHelper.getReadableDatabase().query(Contract.Review.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default: return null;

        }
        output.setNotificationUri(getContext().getContentResolver(),uri);
        return output;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
