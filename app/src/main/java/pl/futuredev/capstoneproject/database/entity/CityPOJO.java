package pl.futuredev.capstoneproject.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.futuredev.capstoneproject.database.converter.ImagesConverter;
import pl.futuredev.capstoneproject.models.Image;

@Entity(tableName = "city", indices = {@Index(value = "name", unique = true)})
public class CityPOJO implements Serializable {

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

    public CityPOJO(int id, @NonNull String name, @NonNull String snippet, List<Image> images, @NonNull String cityId) {
        this.id = id;
        this.name = name;
        this.snippet = snippet;
        this.images = images;
        this.cityId = cityId;
    }

    @Ignore
    public CityPOJO(@NonNull String name, @NonNull String snippet, List<Image> images, @NonNull String cityId) {
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
