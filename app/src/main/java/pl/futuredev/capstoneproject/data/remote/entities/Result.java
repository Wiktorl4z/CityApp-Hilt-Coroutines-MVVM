package pl.futuredev.capstoneproject.data.remote.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("vendor_tour_url")
    @Expose
    private String vendorTourUrl;
    @SerializedName("country_id")
    @Expose
    private String countryId;

    public String getVendorTourUrl() {
        return vendorTourUrl;
    }

    public Price getPrice() {
        return price;
    }

    public Object getPriceTier() {
        return priceTier;
    }

    public List<Attribution> getAttribution() {
        return attribution;
    }

    public String getName() {
        return name;
    }

    public String getLocationId() {
        return locationId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getSnippet() {
        return snippet;
    }

    public Double getScore() {
        return score;
    }

    public Object getSnippetLanguageInfo() {
        return snippetLanguageInfo;
    }

    public List<Image> getImages() {
        return images;
    }

    public Object getBookingInfo() {
        return bookingInfo;
    }

    public String getId() {
        return id;
    }

    public Result() {
    }

    public String getCountryId() {
        return countryId;
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
        dest.writeParcelable(this.price, flags);
        dest.writeString(this.vendorTourUrl);
        dest.writeString(this.countryId);
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
        this.price = in.readParcelable(Price.class.getClassLoader());
        this.vendorTourUrl = in.readString();
        this.countryId = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
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
