package pl.futuredev.capstoneproject.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.futuredev.capstoneproject.data.remote.entities.Image
import java.io.Serializable
import java.lang.reflect.Type

class ImagesConverter : Serializable {

    @TypeConverter
    fun fromIngredientsValuesList(ingredientsPOJOS: List<Image?>?): String? {
        if (ingredientsPOJOS == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Image?>?>() {}.type
        return gson.toJson(ingredientsPOJOS, type)
    }

    @TypeConverter
    fun toIngredientsList(ingredientsList: String?): List<Image>? {
        if (ingredientsList == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Image?>?>() {}.type
        return gson.fromJson<List<Image>>(ingredientsList, type)
    }
}
