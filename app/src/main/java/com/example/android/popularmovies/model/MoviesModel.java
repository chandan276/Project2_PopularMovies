package com.example.android.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviesModel implements Parcelable {

    private int movieId;
    private String moviePosterPath;
    private String movieOriginalName;
    private String movieOverview;
    private String movieReleaseDate;
    private Number movieUserRating;

    public MoviesModel() { }

    public MoviesModel(int movieId, String moviePosterPath, String movieOriginalName, String movieOverview, String movieReleaseDate, Number movieUserRating) {
        this.movieId = movieId;
        this.moviePosterPath = moviePosterPath;
        this.movieOriginalName = movieOriginalName;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
        this.movieUserRating = movieUserRating;
    }

    public MoviesModel(Parcel in) {
        this.movieId = in.readInt();
        this.moviePosterPath = in.readString();
        this.movieOriginalName = in.readString();
        this.movieOverview = in.readString();
        this.movieReleaseDate = in.readString();
        this.movieUserRating = in.readDouble();
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieOriginalName() {
        return movieOriginalName;
    }

    public void setMovieOriginalName(String movieOriginalName) {
        this.movieOriginalName = movieOriginalName;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public Number getMovieUserRating() {
        return movieUserRating;
    }

    public void setMovieUserRating(Number movieUserRating) {
        this.movieUserRating = movieUserRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(moviePosterPath);
        dest.writeString(movieOriginalName);
        dest.writeString(movieOverview);
        dest.writeString(movieReleaseDate);
        dest.writeDouble(movieUserRating.doubleValue());
    }

    public static final Parcelable.Creator<MoviesModel> CREATOR = new Parcelable.Creator<MoviesModel>() {

        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };
}
