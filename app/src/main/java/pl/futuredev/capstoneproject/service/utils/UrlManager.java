package pl.futuredev.capstoneproject.service.utils;

import pl.futuredev.capstoneproject.BuildConfig;

public class UrlManager {

    //Please add your proper API key to project:gradle.properties file API_KEY=""
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String ACCOUNT_ID = "20180627";
    // https://www.triposo.com/api/20180627/location.json?id=London&account=<account ID>&token=<API token>

    public static final String BASE_URL = "https://www.triposo.com/api/20180627/";


    public static final String TEST = "https://www.triposo.com/api/20180627/";

    // https://www.triposo.com/api/20180627/poi.json?location_id=Lublin&account="ACCOUNTID"&token="API_KEY"

    public static final String TOP_PLACES = "api/20180627/poi.json?location_id=Lublin&account=" + ACCOUNT_ID + "&token=" + API_KEY;
}
