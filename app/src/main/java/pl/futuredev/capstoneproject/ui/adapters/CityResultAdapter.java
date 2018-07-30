package pl.futuredev.capstoneproject.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.models.Original;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.ui.interfaces.IOnClickHandler;

public class CityResultAdapter extends RecyclerView.Adapter<CityResultAdapter.ViewHolder> {

    private List<Result> resultList;
    private IOnClickHandler iOnClickHandler;

    public CityResultAdapter(List<Result> resultList, IOnClickHandler iOnClickHandler) {
        this.resultList = resultList;
        this.iOnClickHandler = iOnClickHandler;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_city)
        ImageView ivCity;
        @BindView(R.id.tv_city_name)
        TextView tvCityName;
        @BindView(R.id.tv_city_snippet)
        TextView tvCitySnippet;
        @BindView(R.id.tv_city_score)
        TextView tvCityScore;
        @BindView(R.id.tv_country_id)
        TextView tvCountryId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickPosition = getAdapterPosition();
            iOnClickHandler.onClick(clickPosition);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_city_result, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView ivCity = holder.ivCity;
        TextView tvCityName = holder.tvCityName;
        TextView tvCitySnippet = holder.tvCitySnippet;
        TextView tvCityScore = holder.tvCityScore;
        TextView tvCountryId =holder.tvCountryId;

        List<Image> images = resultList.get(position).getImages();
        if (images != null && !images.isEmpty()) {
            Original originalImage = images.get(0).getSizes().getOriginal();
            Picasso.get()
                    .load(originalImage.getUrl())
                    .into(ivCity, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.get().load(originalImage.getUrl()).into(ivCity);

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(R.drawable.rest1).into(ivCity);
                        }
                    });
        }

        tvCountryId.setText(resultList.get(position).getCountryId());
        Double scores = resultList.get(position).getScore();
        String scoresString = Double.toString(scores);
        tvCityScore.setText(scoresString + " /10");
        tvCityName.setText(resultList.get(position).getName());
        tvCitySnippet.setText(resultList.get(position).getSnippet());
    }


    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
