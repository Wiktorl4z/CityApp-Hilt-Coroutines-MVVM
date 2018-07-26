package pl.futuredev.capstoneproject.service;

import pl.futuredev.capstoneproject.models.Recipe;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET(UrlManager.TOP_PLACES)
    Call<Recipe> getTopPlaces();



}
