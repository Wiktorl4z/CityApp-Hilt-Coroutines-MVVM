package pl.futuredev.capstoneproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable {

    @SerializedName("price_tier")
    @Expose
    private Object priceTier;
    @SerializedName("attribution")
    @Expose
    private List<Attribution> attribution = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("snippet_language_info")
    @Expose
    private Object snippetLanguageInfo;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("booking_info")
    @Expose
    private Object bookingInfo;
    @SerializedName("id")
    @Expose
    private String id;

    public Object getPriceTier() {
        return priceTier;
    }

    public void setPriceTier(Object priceTier) {
        this.priceTier = priceTier;
    }

    public List<Attribution> getAttribution() {
        return attribution;
    }

    public void setAttribution(List<Attribution> attribution) {
        this.attribution = attribution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Object getSnippetLanguageInfo() {
        return snippetLanguageInfo;
    }

    public void setSnippetLanguageInfo(Object snippetLanguageInfo) {
        this.snippetLanguageInfo = snippetLanguageInfo;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Object getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(Object bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.priceTier, flags);
        dest.writeTypedList(this.attribution);
        dest.writeString(this.name);
        dest.writeString(this.locationId);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeString(this.snippet);
        dest.writeValue(this.score);
        dest.writeParcelable((Parcelable) this.snippetLanguageInfo, flags);
        dest.writeTypedList(this.images);
        dest.writeParcelable((Parcelable) this.bookingInfo, flags);
        dest.writeString(this.id);
    }

    public Result() {
    }

    protected Result(Parcel in) {
        this.priceTier = in.readParcelable(Object.class.getClassLoader());
        this.attribution = in.createTypedArrayList(Attribution.CREATOR);
        this.name = in.readString();
        this.locationId = in.readString();
        this.coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        this.snippet = in.readString();
        this.score = (Double) in.readValue(Double.class.getClassLoader());
        this.snippetLanguageInfo = in.readParcelable(Object.class.getClassLoader());
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.bookingInfo = in.readParcelable(Object.class.getClassLoader());
        this.id = in.readString();
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
