package pl.futuredev.capstoneproject.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "city", indices = {@Index(value = "city_id", unique = true)})
public class CityPOJO {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "city_id")
    private int cityId;

    public CityPOJO(int id, @NonNull int cityId) {
        this.id = id;
        this.cityId = cityId;
    }

    @Ignore
    public CityPOJO(@NonNull int cityId) {
        this.cityId = cityId;
    }

    @NonNull
    public int getCityId() {
        return cityId;
    }
}
