package pl.futuredev.capstoneproject.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.futuredev.capstoneproject.ui.IOnClickHandler;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Result;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.ViewHolder> {

    private List<Result> result;
    private IOnClickHandler iOnClickHandler;

    public TopPlacesAdapter(List<Result>result, IOnClickHandler iOnClickHandler) {
        this.result = result;
        this.iOnClickHandler = iOnClickHandler;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     /*   TextView tvReleaseDate;
        ImageView tv_imageView;
        TextView titleText;
        RatingBar ratingBar;
        TextView tvVoteAverage;
        TextView tvVoteCount;*/

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
       /*     this.titleText = itemView.findViewById(R.id.tv_title_text);
            this.tv_imageView = itemView.findViewById(R.id.tv_imageView);
            this.tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            this.ratingBar = itemView.findViewById(R.id.rating_bar);
            this.tvVoteAverage = itemView.findViewById(R.id.tv_vote_average);
            this.tvVoteCount = itemView.findViewById(R.id.tv_vote_count);*/
        }

        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            iOnClickHandler.onClick(clickPosition);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_places_single_layout, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
/*        TextView titleTextView = holder.titleText;
        ImageView imageView = holder.tv_imageView;
        TextView tvReleaseDate = holder.tvReleaseDate;
        RatingBar ratingBar = holder.ratingBar;
        TextView tvVoteAverage = holder.tvVoteAverage;
        TextView tvVoteCount = holder.tvVoteCount;*/
/*
        mCursor.moveToPosition(position);
        String title = mCursor.getString(MainActivity.MOVIE_TITLE);
        titleTextView.setText(title);

        String votes = mCursor.getString(MainActivity.VOTE_AVERAGE);

        String release = mCursor.getString(MainActivity.RELEASE_DATE);
        String year = release.substring(0, Math.min(release.length(), 4));
        tvReleaseDate.setText(year);

        ratingBar.setRating(settingRatingBar(votes));
        tvVoteAverage.setText(mCursor.getString(MainActivity.VOTE_AVERAGE) + mContext.getString(R.string.scores));
        tvVoteCount.setText(mCursor.getString(MainActivity.VOTE_COUNT) + " " + mContext.getString(R.string.votes));

        String imageUrl = UrlManager.IMAGE_BASE_URL;
        String urlId = imageUrl + mCursor.getString(MainActivity.MOVIE_POSTER_PATCH);
        Picasso.get().load(urlId).into(imageView);*/
    }

    @Override
    public int getItemCount() {
       return result.size();
    }
}
