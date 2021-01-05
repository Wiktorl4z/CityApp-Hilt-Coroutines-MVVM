package pl.futuredev.capstoneproject.data.remote

import pl.futuredev.capstoneproject.data.remote.entities.City
import pl.futuredev.capstoneproject.others.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

/*    @GET(UrlManager.TOP_PLACES_TO_SEE)
    fun getTopPlacesToSee(@Query(value = "location_id") locationId: String?): Observable<Recipe?>?

    @GET(UrlManager.TOP_PLACES_TO_EAT)
    fun getTopPlacesToEat(@Query(value = "location_id") locationId: String?): Observable<Recipe?>?

    @GET(UrlManager.TOP_SCORED_TAGS_FOR_LOCATION)
    fun getTopScoredTagsForLocation(@Query(value = "location_ids") locationId: String?): Observable<Recipe?>?

    @GET(UrlManager.FIND_CITY_BY_LOCATION_ID)
    fun getCityByLocationId(
        @Query(
            value = "annotate",
            encoded = true
        ) locationId: String?
    ): Observable<Recipe?>?*/

    @GET(Constants.TOP_PLACES_TO_SEE)
    suspend fun getTopPlacesToSee(
        @Query(value = "location_id")
        locationId: String
    ): Response<City>

    @GET(Constants.TOP_PLACES_TO_EAT)
    suspend fun getTopPlacesToEat(
        @Query(value = "location_id")
        locationId: String
    ): Response<City>

    @GET(Constants.TOP_SCORED_TAGS_FOR_LOCATION)
    suspend fun getTopScoredTagsForLocation(
        @Query(value = "location_ids")
        locationId: String
    ): Response<City>

    @GET(Constants.FIND_CITY_BY_LOCATION_ID)
    suspend fun getCityByNameFromApi(
        @Query(value = "annotate", encoded = true)
        name: String
    ): Response<City>

}