package pl.futuredev.capstoneproject.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import pl.futuredev.capstoneproject.data.local.CityDatabase
import pl.futuredev.capstoneproject.data.remote.CityApi
import pl.futuredev.capstoneproject.others.Constants.BASE_URL
import pl.futuredev.capstoneproject.others.Constants.DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideMainDispatcher() = Dispatchers.Main as CoroutineDispatcher

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
                //    .placeholder(R.drawable.ic_arrow_back)
                //    .error(R.drawable.ic_error)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )

    @Singleton
    @Provides
    fun provideNotesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, CityDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: CityDatabase) = db.cityDao()

    @Singleton
    @Provides
    fun provideCityApi(
    ): CityApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }

}