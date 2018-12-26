package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.model.MoviesModel;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mOriginalNametv;
    private ImageView mPosterIv;
    private TextView mOverviewtv;
    private TextView mReleaseDatetv;
    private TextView mUserRatingtv;

    private MoviesModel movieDetailData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        setTitle(R.string.detail_screen_title);

        mOriginalNametv = (TextView) findViewById(R.id.movie_title);
        mPosterIv = (ImageView) findViewById(R.id.movie_image);
        mReleaseDatetv = (TextView) findViewById(R.id.movie_release_date);
        mUserRatingtv = (TextView) findViewById(R.id.movie_rating);
        mOverviewtv = (TextView) findViewById(R.id.movie_overview);

        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            movieDetailData = (MoviesModel) intent.getParcelableExtra(Intent.EXTRA_TEXT);
        }

        if (movieDetailData != null) {
            populateUI();
        }
    }

    private void populateUI() {
        mOriginalNametv.setText(movieDetailData.getMovieOriginalName());

        String imageUrlString = NetworkUtils.IMAGE_BASE_URL + NetworkUtils.IMAGE_SIZE + movieDetailData.getMoviePosterPath();

        Picasso.with(mPosterIv.getContext())
                .load(imageUrlString)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mPosterIv);

        String formattedReleaseDate = getString(R.string.movie_release_date_prefix) + " " + movieDetailData.getMovieReleaseDate();
        mReleaseDatetv.setText(formattedReleaseDate);

        String formattedUserRating = getString(R.string.movie_user_rating_prefix) + " " + movieDetailData.getMovieUserRating().toString();
        mUserRatingtv.setText(formattedUserRating);

        mOverviewtv.setText(movieDetailData.getMovieOverview());
    }
}
