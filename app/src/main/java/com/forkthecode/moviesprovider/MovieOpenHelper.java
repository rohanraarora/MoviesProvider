package com.forkthecode.moviesprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ralph on 04/11/17.
 */

public class MovieOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "movies_db";

    private static MovieOpenHelper instance;

    public static MovieOpenHelper getInstance(Context context) {
        if(instance == null){
            instance = new MovieOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private MovieOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String moviesSql = "CREATE TABLE " + Contract.Movie.TABLE_NAME + " ( " +
                Contract.Movie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Movie.TITLE + " TEXT, " +
                Contract.Movie.DESCRIPTION + " TEXT)";

        String revSql = "CREATE TABLE " + Contract.Review.TABLE_NAME + " ( " +
                Contract.Review._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Review.REVIEW + " TEXT, " +
                Contract.Review.AUTHOR_NAME + " TEXT)";
        db.execSQL(moviesSql);
        db.execSQL(revSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
