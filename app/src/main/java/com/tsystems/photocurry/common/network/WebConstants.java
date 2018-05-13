package com.tsystems.photocurry.common.network;

/**
 * Created by perry.garg on 10/05/18.
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

    String OK = "ok";

    /**
     * Web Service Task Codes
     */

    int WS_QUERY_IMAGE_SERVICE = 1;


}
