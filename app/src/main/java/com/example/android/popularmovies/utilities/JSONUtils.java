package com.example.android.popularmovies.utilities;

import com.example.android.popularmovies.model.MoviesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private final static String Results_Tag = "results";

    private final static String Id_Tag = "id";

    private final static String Poster_Path_Tag = "poster_path";
    private final static String Original_Name_Tag = "original_title";
    private final static String Overview_Tag = "overview";
    private final static String User_Rating_Tag = "vote_average";
    private final static String Release_Date_Tag = "release_date";

    public static List<MoviesModel> parseMovieListJson(String json) {

        List<MoviesModel> moviesModelList = new ArrayList<>();

        try {
            JSONObject movieJsonObject = new JSONObject(json);
            JSONArray resultsList = movieJsonObject.getJSONArray(Results_Tag);

            ArrayList<JSONObject> arrayList = new ArrayList(resultsList.length());
            for(int i = 0; i < resultsList.length(); i++){
                arrayList.add(resultsList.getJSONObject(i));
            }

            for (final JSONObject resultDict: arrayList) {
                int movieId = resultDict.getInt(Id_Tag);
                String originalName = resultDict.getString(Original_Name_Tag);
                String posterPath = resultDict.getString(Poster_Path_Tag);
                String overview = resultDict.getString(Overview_Tag);
                String releaseDate = resultDict.getString(Release_Date_Tag);
                Number userReview = resultDict.getDouble(User_Rating_Tag);

                MoviesModel movieModel = new MoviesModel(movieId, posterPath, originalName, overview, releaseDate, userReview);

                moviesModelList.add(movieModel);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return moviesModelList;
    }
}
