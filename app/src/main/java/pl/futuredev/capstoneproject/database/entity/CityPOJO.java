package pl.futuredev.capstoneproject.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "city", indices = {@Index(value = "city_name", unique = true)})
public class CityPOJO {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "city_name")
    private String cityName;

    public CityPOJO(int id, @NonNull String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    @Ignore
    public CityPOJO(@NonNull String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }
}
