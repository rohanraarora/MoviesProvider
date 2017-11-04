package com.forkthecode.moviesprovider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int REQUEST_ADD = 10;

    ListView listView;
    ArrayList<Movie> movies = new ArrayList<>();
    MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this,AddMovieActivity.class);
                startActivityForResult(add,REQUEST_ADD);
            }
        });

//        MovieOpenHelper openHelper = MovieOpenHelper.getInstance(this);
//        SQLiteDatabase database = openHelper.getReadableDatabase();
//        Cursor cursor = database.query(Contract.Movie.TABLE_NAME,null,null,null,null,null,null );
//        while (cursor.moveToNext()){
//            String title = cursor.getString(cursor.getColumnIndex(Contract.Movie.TITLE));
//            String desc = cursor.getString(cursor.getColumnIndex(Contract.Movie.DESCRIPTION));
//            Movie movie = new Movie(title,desc);
//            movies.add(movie);
//        }

        listView = (ListView)findViewById(R.id.listView);
        adapter = new MoviesAdapter(this,movies);
        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(1,null,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_ADD && resultCode == RESULT_OK){
////            long id = data.getLongExtra(Constant.KEY_MOVIE_ID,-1l);
////            if(id > -1){
////                MovieOpenHelper openHelper = MovieOpenHelper.getInstance(this);
////                SQLiteDatabase database = openHelper.getReadableDatabase();
////
////                Cursor cursor = database.query(Contract.Movie.TABLE_NAME,null,Contract.Movie._ID + " = ?",new String[]{id+""},null,null,null );
//
////
////            }
//
//            Uri uri = data.getData();
//            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
//            if(cursor != null && cursor.moveToFirst()){
//                String title = cursor.getString(cursor.getColumnIndex(Contract.Movie.TITLE));
//                String desc = cursor.getString(cursor.getColumnIndex(Contract.Movie.DESCRIPTION));
//                Movie movie = new Movie(title,desc);
//                movies.add(movie);
//                adapter.notifyDataSetChanged();
//            }
//        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,Contract.Movie.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        movies.clear();
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(Contract.Movie.TITLE));
            String desc = cursor.getString(cursor.getColumnIndex(Contract.Movie.DESCRIPTION));
            Movie movie = new Movie(title,desc);
            movies.add(movie);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        movies.clear();
    }
}
