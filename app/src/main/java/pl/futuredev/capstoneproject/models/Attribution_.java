package pl.futuredev.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attribution_ implements Parcelable {

    @SerializedName("license_link")
    @Expose
    private String licenseLink;
    @SerializedName("attribution_link")
    @Expose
    private String attributionLink;
    @SerializedName("attribution_text")
    @Expose
    private String attributionText;
    @SerializedName("license_text")
    @Expose
    private String licenseText;
    @SerializedName("format")
    @Expose
    private String format;

    public String getLicenseLink() {
        return licenseLink;
    }

    public void setLicenseLink(String licenseLink) {
        this.licenseLink = licenseLink;
    }

    public String getAttributionLink() {
        return attributionLink;
    }

    public void setAttributionLink(String attributionLink) {
        this.attributionLink = attributionLink;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getLicenseText() {
        return licenseText;
    }

    public void setLicenseText(String licenseText) {
        this.licenseText = licenseText;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.licenseLink);
        dest.writeString(this.attributionLink);
        dest.writeString(this.attributionText);
        dest.writeString(this.licenseText);
        dest.writeString(this.format);
    }

    public Attribution_() {
    }

    protected Attribution_(Parcel in) {
        this.licenseLink = in.readString();
        this.attributionLink = in.readString();
        this.attributionText = in.readString();
        this.licenseText = in.readString();
        this.format = in.readString();
    }

    public static final Creator<Attribution_> CREATOR = new Creator<Attribution_>() {
        @Override
        public Attribution_ createFromParcel(Parcel source) {
            return new Attribution_(source);
        }

        @Override
        public Attribution_[] newArray(int size) {
            return new Attribution_[size];
        }
    };
}
