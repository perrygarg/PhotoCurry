package com.tsystems.photocurry.home.service;

import com.android.volley.Request;
import com.tsystems.photocurry.application.MyApplication;
import com.tsystems.photocurry.common.network.AppHttpClient;
import com.tsystems.photocurry.common.network.WebConstants;
import com.tsystems.photocurry.common.network.WebManager;
import com.tsystems.photocurry.common.network.WebService;
import com.tsystems.photocurry.common.network.WebServiceListener;
import com.tsystems.photocurry.home.model.SearchPhotoResponse;
import com.tsystems.photocurry.home.model.SearchRequest;

/**
 * Created by PerryGarg on 11-05-2018.
 */

public class FetchQueryImagesService extends WebService {

    public FetchQueryImagesService(int taskCode, WebServiceListener serviceListener) {
        super(taskCode, serviceListener);
    }

    @Override
    public void getData(Object... args) {
        SearchRequest request = (SearchRequest) args[0];

        String endpoint = WebConstants.URL_FETCH_QUERY_IMAGES_1 + request.queryText + WebConstants.URL_FETCH_QUERY_IMAGES_2 + request.pageNumber + WebConstants.URL_FETCH_QUERY_IMAGES_3 + request.photosPerPage + WebConstants.URL_FETCH_QUERY_IMAGES_4;
        AppHttpClient client = new AppHttpClient(MyApplication.appContext);
        client.sendGSONRequest(Request.Method.GET, endpoint, null, SearchPhotoResponse.class, WebManager.getHeaders(), this, this, tag);
    }

    @Override
    public void onResponse(Object object) {
        SearchPhotoResponse response = (SearchPhotoResponse) object;

        if(response.status.equals(WebConstants.OK)) {
            serviceListener.onServiceSuccess(response, taskCode);
        } else {
            serviceListener.onServiceError(WebConstants.API_ERROR, taskCode, 0);
        }

    }
}
