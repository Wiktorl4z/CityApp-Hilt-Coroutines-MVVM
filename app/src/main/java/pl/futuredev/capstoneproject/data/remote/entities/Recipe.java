package pl.futuredev.capstoneproject.data.remote.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("estimated_total")
    @Expose
    private Integer estimatedTotal;
    @SerializedName("more")
    @Expose
    private Boolean more;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getEstimatedTotal() {
        return estimatedTotal;
    }

    public void setEstimatedTotal(Integer estimatedTotal) {
        this.estimatedTotal = estimatedTotal;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
        dest.writeValue(this.estimatedTotal);
        dest.writeValue(this.more);
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.results = new ArrayList<Result>();
        in.readList(this.results, Result.class.getClassLoader());
        this.estimatedTotal = (Integer) in.readValue(Integer.class.getClassLoader());
        this.more = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
