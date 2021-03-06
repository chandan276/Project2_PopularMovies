package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.model.MoviesModel;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {



    final private MovieItemClickListener mOnClickListener;

    private int mNumberItems;
    private List<MoviesModel> movieDataList = new ArrayList<>();

    public interface MovieItemClickListener {
        void onMovieItemClick(int clickedItemIndex);
    }

    public MoviesAdapter(int numberOfItems, MovieItemClickListener listener, List<MoviesModel> movieList) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        movieDataList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Context context = holder.movieImageView.getContext();
        MoviesModel movieModel = movieDataList.get(position);
        String imageUrlString = NetworkUtils.IMAGE_BASE_URL + NetworkUtils.IMAGE_SIZE + movieModel.getMoviePosterPath();

        Picasso.with(context)
                .load(imageUrlString)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.movieImageView);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movieImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImageView = (ImageView) itemView.findViewById(R.id.image_movie);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onMovieItemClick(clickedPosition);
        }
    }
}
