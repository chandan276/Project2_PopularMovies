package com.example.android.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String TMDB_BASE_URL = "http://api.themoviedb.org/3/movie/";
    final static String SORT_CATEGORY_POPULAR = "popular";
    final static String SORT_CATEGORY_TOP_RATED = "top_rated";

    final static String PARAM_KEY = "api_key";
    final static String API_KEY = "fb971a3a96deb1ed6ae0c663a39df1ad"; //Add API_KEY to run the app.

    final static String DELIMITER = "?";

    final public static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    final public static String IMAGE_SIZE = "w185";

    public static URL buildUrlForMovieList(int sortOrder) {

        String sortingOrder = sortOrder == 0 ? SORT_CATEGORY_POPULAR : SORT_CATEGORY_TOP_RATED;
        String baseUrl = TMDB_BASE_URL + sortingOrder + DELIMITER;
        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(PARAM_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
