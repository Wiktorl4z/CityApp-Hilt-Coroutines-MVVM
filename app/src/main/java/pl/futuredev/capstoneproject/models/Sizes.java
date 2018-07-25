package pl.futuredev.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sizes implements Parcelable {

    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("original")
    @Expose
    private Original original;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.original, flags);
        dest.writeParcelable(this.thumbnail, flags);
    }

    public Sizes() {
    }

    protected Sizes(Parcel in) {
        this.medium = in.readParcelable(Medium.class.getClassLoader());
        this.original = in.readParcelable(Original.class.getClassLoader());
        this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
    }

    public static final Parcelable.Creator<Sizes> CREATOR = new Parcelable.Creator<Sizes>() {
        @Override
        public Sizes createFromParcel(Parcel source) {
            return new Sizes(source);
        }

        @Override
        public Sizes[] newArray(int size) {
            return new Sizes[size];
        }
    };
}
