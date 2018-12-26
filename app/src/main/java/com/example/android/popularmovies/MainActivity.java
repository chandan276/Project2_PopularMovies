package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.popularmovies.model.MoviesModel;
import com.example.android.popularmovies.utilities.JSONUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MovieItemClickListener {

    private static final int GRID_SPAN = 2;

    private MoviesAdapter mAdapter;
    private RecyclerView mMovieList;

    private int selectedMenuOption = 0;
    private List<MoviesModel> movieDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set home screen title
        setPageTitle();

        mMovieList = (RecyclerView) findViewById(R.id.recyclerview_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, GRID_SPAN);
        mMovieList.setLayoutManager(layoutManager);

        mMovieList.setHasFixedSize(true);

        upadteAdapter();

        makeMovieListRequest();
    }

    private void setPageTitle() {

        String homeTitle = getString(R.string.home_screen_title);
        String postFix = selectedMenuOption == 0 ? getString(R.string.menu_option_most_popular) : getString(R.string.menu_option_top_rated);

        setTitle(homeTitle + " - " + postFix);
    }

    private void upadteAdapter() {
        mAdapter = new MoviesAdapter(movieDataList.size(), this, movieDataList);
        mMovieList.setAdapter(mAdapter);
    }

    private void makeMovieListRequest() {
        URL movieListUrl = NetworkUtils.buildUrlForMovieList(selectedMenuOption);
        new MovieQueryTask().execute(movieListUrl);
    }

    private void showErrorMessage() {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMovieItemClick(int clickedItemIndex) {
        Context context = MainActivity.this;
        Class destinationActivity = MovieDetailActivity.class;
        Intent intent = new Intent(context, destinationActivity);
        MoviesModel movieModel = movieDataList.get(clickedItemIndex);
        intent.putExtra(Intent.EXTRA_TEXT, movieModel);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.action_most_popular) {
            selectedMenuOption = 0;
        } else if (itemThatWasClickedId == R.id.action_top_rated) {
            selectedMenuOption = 1;
        }
        setPageTitle();
        makeMovieListRequest();
        return true;
    }

    public class MovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String movieResults = null;
            try {
                movieResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieResults;
        }

        @Override
        protected void onPostExecute(String movieResults) {

            if (movieResults != null && !movieResults.equals("")) {
                movieDataList = JSONUtils.parseMovieListJson(movieResults);
                upadteAdapter();
            } else {
                showErrorMessage();
            }
        }
    }
}
