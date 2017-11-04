package com.forkthecode.moviesprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMovieActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        titleEditText = (EditText)findViewById(R.id.title);
        descEditText = (EditText)findViewById(R.id.description);
    }

    public void add(View view){

        String title = titleEditText.getEditableText().toString();
        String desc = descEditText.getEditableText().toString();


//        MovieOpenHelper openHelper = MovieOpenHelper.getInstance(this);
//        SQLiteDatabase database = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Movie.TITLE,title);
        contentValues.put(Contract.Movie.DESCRIPTION,desc);

//        long id =database.insert(Contract.Movie.TABLE_NAME,null,contentValues);

        Uri data = getContentResolver().insert(Contract.Movie.CONTENT_URI,contentValues);

        Intent result = new Intent();
        result.setData(data);
//        result.putExtra(Constant.KEY_MOVIE_ID,id);
        setResult(RESULT_OK,result);
        finish();



    }
}
