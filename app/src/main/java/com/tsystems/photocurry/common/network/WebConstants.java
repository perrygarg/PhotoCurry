package com.tsystems.photocurry.common.network;

/**
 * Created by perry.garg on 30/01/17.
 */

public interface WebConstants {


    String FLICKR_IMG_1 = "https://farm";
    String FLICKR_IMG_2 = ".staticflickr.com/";
    String SEPARATOR = "/";
    String UNDERSCORE = "_";
    String FLICKR_IMG_3 = ".jpg";

    String SMALL = "_n";
    String MEDIUM = "_z";
    String LARGE = "_h";

    String API_KEY = "&api_key=3a6e7cf8f8338cf32f8e7e213dd202c4";
    String API_BASE_URL = "https://api.flickr.com/services/rest/";
    String API_PHOTO_SEARCH_METHOD = "?method=flickr.photos.search";
    String API_QUERY_TEXT = "&text=";
    String URL_FETCH_QUERY_IMAGES_1 = API_BASE_URL + API_PHOTO_SEARCH_METHOD + API_KEY + API_QUERY_TEXT;
    String URL_FETCH_QUERY_IMAGES_2 = "&privacy_filter=1&page=";
    String URL_FETCH_QUERY_IMAGES_3 = "&per_page=";
    String URL_FETCH_QUERY_IMAGES_4 = "&format=json&nojsoncallback=1";

    String API_ERROR = "API key has expired. Please contact developer.";

    /**
     * Web Service Task Codes
     */

    int WS_QUERY_IMAGE_SERVICE = 1;



    int FETCH_CONFIG_SERVICE = 2;
    int FETCH_TRAIN_ROUTE_SERVICE = 3;
    int CHECK_TRAIN_LIVE_API_SERVICE = 4;
    int FETCH_TRAIN_LIST_SERVICE = 5;
    int FETCH_PNR_DETAILS_SERVICE = 6;

    int FETCH_SELECTED_STATION_DEFAULT_RESTAURANT_DATA_SERVICE = 7;

    String RAIL_API_KEY = "/apikey/7fdrpwm74l";
    String VERSION = "v2/";
    String DATE = "/date/";

    /**
     * Web Service URLs
     */
    //String BASE_URL = ""; //Production Server
    String BASE_URL_RAIL_API = "http://api.railwayapi.com/";
    //String URL_LOGIN = BASE_URL + "v1/business/login";
    String URL_TRAIN_AUTOCOMPLETE = BASE_URL_RAIL_API + VERSION + "suggest-train/train/";
    String URL_TRAIN_ROUTE = BASE_URL_RAIL_API + VERSION + "route/train/";
    String URL_TRAIN_LIVE_STATUS = BASE_URL_RAIL_API + VERSION + "live/train/";
    String URL_FETCH_PNR_DETAILS = BASE_URL_RAIL_API + VERSION + "pnr-status/pnr/";

//    http://api.railwayapi.com/v2/live/train/12045/date/11-09-2017/apikey/m4h2fi5539/

    //Firebase schemas
    String CONFIG_SCHEMA = "config";
    String TRAIN_LIST_SCHEMA = "train_list";

    String FOOD_ITEMS_SCHEMA = "food_items";
    String IS_DEFAULT = "is_default";

}
