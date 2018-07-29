package pl.futuredev.capstoneproject.service;

import pl.futuredev.capstoneproject.BuildConfig;
import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String ACCOUNT_ID = BuildConfig.ACCOUNT_ID;

    @GET(UrlManager.TOP_PLACES_TO_SEE)
    Call<Recipe> getTopPlacesToSee();

    @GET(UrlManager.TOP_PLACES_TO_EAT)
    Call<Recipe> getTopPlacesToEat();

    @GET(UrlManager.TOP_SCORED_TAGS_FOR_LOCATION)
    Call<Recipe> getTopScoredTagsForLocation();

    @GET(UrlManager.FIND_CITY_BY_LOCATION_ID)
    Call<Recipe> getCityByLocationId(@Query(value = "location_id") String locationId);

    @GET(UrlManager.TEST_FIND_CITY_BY_LOCATION_ID)
    Call<Recipe> getCityByLocationId();

    @GET("api/20180627/location.json?tag_labels=city&annotate=trigram:location_id&trigram=%3E=0.3&count=10&fields=id,name,score,country_id,parent_id,snippet,images&order_by=-trigram&account="+ACCOUNT_ID +"&token="+API_KEY)
    Call<Recipe> testGetCityByLocationId(@Query(value = "location_id") String locationId);
}
