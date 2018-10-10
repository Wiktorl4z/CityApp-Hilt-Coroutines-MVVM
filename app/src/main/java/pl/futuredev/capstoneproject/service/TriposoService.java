package pl.futuredev.capstoneproject.service;

import io.reactivex.Flowable;
import pl.futuredev.capstoneproject.BuildConfig;
import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriposoService {

    @GET(UrlManager.TOP_PLACES_TO_SEE)
    Call<Recipe> getTopPlacesToSee(@Query(value = "location_id") String locationId);

    @GET(UrlManager.TOP_PLACES_TO_EAT)
    Call<Recipe> getTopPlacesToEat(@Query(value = "location_id") String locationId);

    @GET(UrlManager.TOP_SCORED_TAGS_FOR_LOCATION)
    Call<Recipe> getTopScoredTagsForLocation(@Query(value = "location_ids") String locationId);

    @GET(UrlManager.FIND_CITY_BY_LOCATION_ID)
    Call<Recipe> getCityByLocationId(@Query(value = "annotate", encoded = true) String locationId);

}
