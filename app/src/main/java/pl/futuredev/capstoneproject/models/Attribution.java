package pl.futuredev.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attribution implements Parcelable {

    @SerializedName("source_id")
    @Expose
    private String sourceId;
    @SerializedName("url")
    @Expose
    private String url;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sourceId);
        dest.writeString(this.url);
    }

    public Attribution() {
    }

    protected Attribution(Parcel in) {
        this.sourceId = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Attribution> CREATOR = new Creator<Attribution>() {
        @Override
        public Attribution createFromParcel(Parcel source) {
            return new Attribution(source);
        }

        @Override
        public Attribution[] newArray(int size) {
            return new Attribution[size];
        }
    };
}
