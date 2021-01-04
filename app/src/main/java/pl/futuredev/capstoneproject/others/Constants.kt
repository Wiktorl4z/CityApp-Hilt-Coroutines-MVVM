package pl.futuredev.capstoneproject.others

import pl.futuredev.capstoneproject.BuildConfig

object Constants {

    const val DATABASE_NAME = "cities_db"
    const val BASE_URL = "https://www.triposo.com"
    const val API_KEY = BuildConfig.API_KEY
    const val ACCOUNT_ID = BuildConfig.ACCOUNT_ID
    const val TOP_PLACES_TO_SEE =
        "api/20180627/poi.json?&fields=all&account=$ACCOUNT_ID&token=$API_KEY"
    const val TOP_PLACES_TO_EAT =
        "api/20180627/poi.json?&tag_labels=eatingout&count=10&order_by=-score&account=$ACCOUNT_ID&token=$API_KEY"
    const val TOP_SCORED_TAGS_FOR_LOCATION =
        "api/20180627/tour.json?count=10&fields=id,name,score,images,price,price_is_per_person,vendor,intro,tag_labels&order_by=-score&account=$ACCOUNT_ID&token=$API_KEY"
    const val FIND_CITY_BY_LOCATION_ID =
        "api/20180627/location.json?tag_labels=city&trigram=%3E=0.3&count=10&fields=id,name,score,country_id,parent_id,snippet,images&order_by=-score&account=$ACCOUNT_ID&token=$API_KEY"
    const val FIND_CITY_MY_ID =
        "api/20180627/location.json?id=London&fields=all&account=$ACCOUNT_ID&token=$API_KEY"


}