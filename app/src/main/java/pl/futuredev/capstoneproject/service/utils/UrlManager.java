package pl.futuredev.capstoneproject.service.utils;

import pl.futuredev.capstoneproject.BuildConfig;

public class UrlManager {

    //Please add your proper API key to project:gradle.properties file API_KEY=""
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String ACCOUNT_ID = BuildConfig.ACCOUNT_ID;
    // https://www.triposo.com/api/20180627/location.json?id=London&account=<account ID>&token=<API token>
    // TODO change forced city to city_id
    // https://www.triposo.com/api/20180627/poi.json?location_id=Lublin&account="ACCOUNTID"&token="API_KEY"
    public static final String TOP_PLACES_TO_SEE = "api/20180627/poi.json?location_id=Lublin&account=" + ACCOUNT_ID + "&token=" + API_KEY;
    // https://www.triposo.com/api/20180627/poi.json?location_id=Lublin&tag_labels=eatingout&count=10&order_by=-score&account=ACCOUNTID&token=API_KEY
    public static final String TOP_PLACES_TO_EAT = "api/20180627/poi.json?location_id=Lublin&tag_labels=eatingout&count=10&order_by=-score&account=" + ACCOUNT_ID + "&token=" + API_KEY;
    // https://www.triposo.com/api/20180627/tour.json?location_ids=Lublin&account=ACCOUNTID&token=API_KEY
    public static final String TOP_SCORED_TAGS_FOR_LOCATION = "api/20180627/tour.json?location_ids=London&account=" + ACCOUNT_ID + "&token=" + API_KEY;
    // https://www.triposo.com/api/20180627/location.json?location_ids=Lublin&account=ACCOUNTID&token=API_KEY
    public static final String FIND_CITY_BY_LOCATION_ID = "api/20180627/location.json?location_ids={location_id}&account=" + ACCOUNT_ID + "&token=" + API_KEY;
}
