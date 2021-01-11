package pl.futuredev.capstoneproject.data.local.entities

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import pl.futuredev.capstoneproject.data.local.converters.ImagesConverter

@Entity
data class City constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    val snippet: String,
    @TypeConverters(ImagesConverter::class)
    val images: List<Image>,
    val cityId: String
) {
}