package com.forkthecode.moviesprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ralph on 04/11/17.
 */

public class Contract {

    public static final String AUTHORITY = "com.forkthecode.moviesprovider";

    public static final String PATH_MOVIES = "movies";

    public static final String PATH_REVIEWS = "reviews";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static final String BASE_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd." + AUTHORITY + ".";
    public static final String BASE_CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd." + AUTHORITY + ".";



    public static class Movie implements BaseColumns {

        public static final String TABLE_NAME = "movies";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";


        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static Uri buildUriForId(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String CONTENT_ITEM_TYPE = BASE_CONTENT_ITEM_TYPE + PATH_MOVIES;
        public static final String CONTENT_TYPE = BASE_CONTENT_TYPE + PATH_MOVIES;

    }

    public static class Review implements BaseColumns{
        public static final String TABLE_NAME = "reviews";
        public static final String REVIEW = "review";
        public static final String AUTHOR_NAME = "author";

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_REVIEWS).build();

        public static Uri buildUriForId(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String CONTENT_ITEM_TYPE = BASE_CONTENT_ITEM_TYPE + PATH_REVIEWS;
        public static final String CONTENT_TYPE = BASE_CONTENT_TYPE + PATH_REVIEWS;


    }

}
