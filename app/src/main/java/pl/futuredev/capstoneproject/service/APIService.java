package pl.futuredev.capstoneproject.service;

import java.util.List;

import pl.futuredev.capstoneproject.models.Result;
import pl.futuredev.capstoneproject.service.utils.UrlManager;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET(UrlManager.TOP_PLACES)
    Call<List<Result>> getTopPlaces();



}
