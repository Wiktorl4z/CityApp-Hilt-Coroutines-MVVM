package pl.futuredev.capstoneproject.service;

import java.util.List;

import io.reactivex.Observable;
import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriposoService {

    @GET(UrlManager.TOP_PLACES_TO_SEE)
    Observable<Recipe> getTopPlacesToSee(@Query(value = "location_id") String locationId);

    @GET(UrlManager.TOP_PLACES_TO_EAT)
    Observable<Recipe> getTopPlacesToEat(@Query(value = "location_id") String locationId);

    @GET(UrlManager.TOP_SCORED_TAGS_FOR_LOCATION)
    Observable<Recipe> getTopScoredTagsForLocation(@Query(value = "location_ids") String locationId);

    @GET(UrlManager.FIND_CITY_BY_LOCATION_ID)
    Observable<Recipe> getCityByLocationId(@Query(value = "annotate", encoded = true) String locationId);

}
