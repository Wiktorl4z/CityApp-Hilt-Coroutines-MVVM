package pl.futuredev.capstoneproject.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import pl.futuredev.capstoneproject.models.Image;


public class ImagesConverter implements Serializable {

    @TypeConverter
    public String fromIngredientsValuesList(List<Image> ingredientsPOJOS) {
        if (ingredientsPOJOS == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Image>>() {
        }.getType();
        String json = gson.toJson(ingredientsPOJOS, type);
        return json;
    }

    @TypeConverter
    public List<Image> toIngredientsList(String ingredientsList) {
        if (ingredientsList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Image>>() {
        }.getType();
        List<Image> productCategoriesList = gson.fromJson(ingredientsList, type);
        return productCategoriesList;
    }
}
