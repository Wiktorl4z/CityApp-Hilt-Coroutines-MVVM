package pl.futuredev.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("attribution")
    @Expose
    private Attribution_ attribution;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("sizes")
    @Expose
    private Sizes sizes;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("source_id")
    @Expose
    private String sourceId;
    @SerializedName("owner_url")
    @Expose
    private String ownerUrl;

    public Attribution_ getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution_ attribution) {
        this.attribution = attribution;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getOwnerUrl() {
        return ownerUrl;
    }

    public void setOwnerUrl(String ownerUrl) {
        this.ownerUrl = ownerUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.attribution, flags);
        dest.writeString(this.license);
        dest.writeParcelable(this.sizes, flags);
        dest.writeString(this.owner);
        dest.writeString(this.sourceUrl);
        dest.writeString(this.caption);
        dest.writeString(this.sourceId);
        dest.writeString(this.ownerUrl);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.attribution = in.readParcelable(Attribution_.class.getClassLoader());
        this.license = in.readString();
        this.sizes = in.readParcelable(Sizes.class.getClassLoader());
        this.owner = in.readString();
        this.sourceUrl = in.readString();
        this.caption = in.readString();
        this.sourceId = in.readString();
        this.ownerUrl = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
