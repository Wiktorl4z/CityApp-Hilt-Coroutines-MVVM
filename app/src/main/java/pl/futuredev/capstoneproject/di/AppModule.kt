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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.futuredev.capstoneproject.data.local.CityDatabase
import pl.futuredev.capstoneproject.data.remote.CityApi
import pl.futuredev.capstoneproject.others.Constants.BASE_URL
import pl.futuredev.capstoneproject.others.Constants.DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
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
    fun provideCityDao(db: CityDatabase) = db.cityDao()


    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message)
            }
        })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideCityApi(loggingInterceptor: HttpLoggingInterceptor): CityApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }

}