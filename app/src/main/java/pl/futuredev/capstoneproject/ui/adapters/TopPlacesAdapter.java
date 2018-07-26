package pl.futuredev.capstoneproject.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.futuredev.capstoneproject.R;
import pl.futuredev.capstoneproject.models.Image;
import pl.futuredev.capstoneproject.models.Medium;
import pl.futuredev.capstoneproject.models.Original;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.models.Thumbnail;
import pl.futuredev.capstoneproject.ui.IOnClickHandler;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.ViewHolder> {


    private List<Result> resultList;
    private IOnClickHandler iOnClickHandler;

    public TopPlacesAdapter(List<Result> resultList, IOnClickHandler iOnClickHandler) {
        this.resultList = resultList;
        this.iOnClickHandler = iOnClickHandler;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_snippet)
        TextView tvSnippet;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
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

        ImageView ivImage = holder.ivImage;
        TextView tvName = holder.tvName;
        TextView tvSnippet = holder.tvSnippet;

        List<Image> images = resultList.get(position).getImages();
        if (images != null && !images.isEmpty()) {
            Medium mediumImage = images.get(0).getSizes().getMedium();

            Picasso.get()
                    .load(mediumImage.getUrl())
                    .into(ivImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.get().load(mediumImage.getUrl()).into(ivImage);

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(R.drawable.rest1).into(ivImage);
                        }
        });

        }

        tvName.setText(resultList.get(position).getName());
        tvSnippet.setText(resultList.get(position).getSnippet());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
