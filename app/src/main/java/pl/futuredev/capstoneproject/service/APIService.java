package pl.futuredev.capstoneproject.service;

import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET(UrlManager.TOP_PLACES_TO_SEE)
    Call<Recipe> getTopPlacesToSee();

    @GET(UrlManager.TOP_PLACES_TO_EAT)
    Call<Recipe> getTopPlacesToEat();

    @GET(UrlManager.TOP_SCORED_TAGS_FOR_LOCATION)
    Call<Recipe> getTopScoredTagsForLocation();

    @GET(UrlManager.FIND_CITY_BY_LOCATION_ID)
    Call<Recipe> getCityByLocationId(@Path(value = "location_id") String locationId);

    @GET(UrlManager.TEST_FIND_CITY_BY_LOCATION_ID)
    Call<Recipe> getCityByLocationId();
}
