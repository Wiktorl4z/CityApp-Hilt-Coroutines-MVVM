package pl.futuredev.capstoneproject.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import pl.futuredev.capstoneproject.data.local.converters.ImagesConverter
import pl.futuredev.capstoneproject.data.remote.entities.Image

@Entity
data class City (
    val name: String,
    val snippet: String,
    @TypeConverters(ImagesConverter::class)
    val image: List<Image>? = null,
    val cityId: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}