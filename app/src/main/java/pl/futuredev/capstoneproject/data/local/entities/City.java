package pl.futuredev.capstoneproject.data.local.entities;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.futuredev.capstoneproject.data.local.converters.ImagesConverter;

@Entity(indices = {@Index(value = "name", unique = true)})
public class City implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "city_snippet")
    private String snippet;
    @TypeConverters(ImagesConverter.class)
    @ColumnInfo(name = "images")
    private List<Image> images = null;
    @NonNull
    @ColumnInfo(name = "city_id")
    private String cityId;

    public City(int id, @NonNull String name, @NonNull String snippet, List<Image> images, @NonNull String cityId) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.images = images;
        this.cityId = cityId;
    }

    @Ignore
    public City(@NonNull String name, @NonNull String snippet, List<Image> images, @NonNull String cityId) {
        this.name = name;
        this.snippet = snippet;
        this.images = images;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getSnippet() {
        return snippet;
    }

    @NonNull
    public String getCityId() {
        return cityId;
    }

    public List<Image> getImages() {
        if (images == null) {
            images = new ArrayList<>();
            return images;
        }
        return images;
    }
}
