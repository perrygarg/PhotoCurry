package com.tsystems.photocurry.home.presenter;

import com.tsystems.photocurry.common.model.MasterResponse;
import com.tsystems.photocurry.common.model.ValidationError;
import com.tsystems.photocurry.common.network.WebConstants;
import com.tsystems.photocurry.common.network.WebManager;
import com.tsystems.photocurry.common.network.WebService;
import com.tsystems.photocurry.common.network.WebServiceListener;
import com.tsystems.photocurry.common.util.AppUtil;
import com.tsystems.photocurry.home.contract.HomeContract;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.model.SearchPhotoResponse;
import com.tsystems.photocurry.home.model.SearchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PerryGarg on 11-05-2018.
 */

/**
 * Presenter class to interact with activity and service classes
 */
public class HomePresenter implements HomeContract.Presenter, WebServiceListener {
    WeakReference<HomeContract.View> viewWeakReference;

    /**
     * Constructor of presenter which initializes Weak Reference of the View. It should be called from relevant activity.
     * @param view view instance
     */
    public HomePresenter(HomeContract.View view) {
        viewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void fetchPhotosWithQuery(int pageNumber, int photosPerPage, String queryText) {
        if(validateInput(queryText)) {
            doFetchPhotosWithQuery(pageNumber, photosPerPage, queryText);
        } else {
            if(!this.isViewObjectNull()) {
                viewWeakReference.get().showSnackBarMessage("Invalid Query");
            }
        }
    }

    /**
     * Method to call API to fetch images against query text
     * @param pageNumber Page Number
     * @param photosPerPage Photos Per Page
     * @param queryText Query text
     */
    private void doFetchPhotosWithQuery(int pageNumber, int photosPerPage, String queryText) {
        SearchRequest request = new SearchRequest();
        request.pageNumber = pageNumber;
        request.photosPerPage = photosPerPage;
        request.queryText = queryText;

        WebService webService = WebManager.getService(WebConstants.WS_QUERY_IMAGE_SERVICE, this);
        webService.getData(request);


        String response = "{\n" +
                "    \"photos\": {\n" +
                "        \"page\": 1,\n" +
                "        \"pages\": 10890,\n" +
                "        \"perpage\": 30,\n" +
                "        \"total\": \"326691\",\n" +
                "        \"photo\": [\n" +
                "            {\n" +
                "                \"id\": \"41175802535\",\n" +
                "                \"owner\": \"24406544@N00\",\n" +
                "                \"secret\": \"fcb844134a\",\n" +
                "                \"server\": \"966\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"Pont Neuf - Paris (France)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"27205645277\",\n" +
                "                \"owner\": \"88558878@N04\",\n" +
                "                \"secret\": \"c23ccd76c8\",\n" +
                "                \"server\": \"972\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"Women Leather Bomber Jacket Fashion\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"40268012530\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"667114b9d0\",\n" +
                "                \"server\": \"831\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (133)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"27205642587\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"f136c5b525\",\n" +
                "                \"server\": \"970\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (134)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"41356191894\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"5df0375b43\",\n" +
                "                \"server\": \"949\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (135)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"28203281788\",\n" +
                "                \"owner\": \"124995914@N07\",\n" +
                "                \"secret\": \"e0b16cb0d0\",\n" +
                "                \"server\": \"955\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"Happy+Love\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"40267999240\",\n" +
                "                \"owner\": \"162417118@N08\",\n" +
                "                \"secret\": \"ecf50eabb9\",\n" +
                "                \"server\": \"972\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"Hair Color 2017/ 2018  - Rose gold hair color will definitely make you stand out, creating a girlish and ...\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075529451\",\n" +
                "                \"owner\": \"145358245@N06\",\n" +
                "                \"secret\": \"1804f73676\",\n" +
                "                \"server\": \"981\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"i Love You Mom\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"28203272788\",\n" +
                "                \"owner\": \"152016880@N03\",\n" +
                "                \"secret\": \"d2df17c174\",\n" +
                "                \"server\": \"960\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"Love me ❤️ my Dog \uD83D\uDC36\uD83D\uDC3E\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"40267985460\",\n" +
                "                \"owner\": \"148866285@N05\",\n" +
                "                \"secret\": \"f9de7299cc\",\n" +
                "                \"server\": \"977\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"Falling In Love\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"28203257388\",\n" +
                "                \"owner\": \"80048637@N02\",\n" +
                "                \"secret\": \"cb415e4a14\",\n" +
                "                \"server\": \"952\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"#Quotes “Do justice, love mercy, walk humbly\\\"—Micah the Prophet [1071x1520] [OC]\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075479551\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"fd1a021028\",\n" +
                "                \"server\": \"950\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (136)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075478231\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"1eab92686f\",\n" +
                "                \"server\": \"912\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (137)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075477931\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"f35da4c174\",\n" +
                "                \"server\": \"827\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (138)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075477501\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"e981f98c85\",\n" +
                "                \"server\": \"943\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (140)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075477611\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"e296b2fa28\",\n" +
                "                \"server\": \"948\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (139)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075477211\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"9594502c28\",\n" +
                "                \"server\": \"948\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (141)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075476901\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"04d6ce53fa\",\n" +
                "                \"server\": \"966\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (143)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075477031\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"dc9a783656\",\n" +
                "                \"server\": \"967\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (142)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075476731\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"da3861ebc5\",\n" +
                "                \"server\": \"979\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (144)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42075476661\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"311ff4ca6b\",\n" +
                "                \"server\": \"903\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (145)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030522502\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"42cc2a2a61\",\n" +
                "                \"server\": \"824\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (146)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030522332\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"94d5f534e2\",\n" +
                "                \"server\": \"974\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (147)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030522222\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"fc66f25fc0\",\n" +
                "                \"server\": \"955\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (148)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030522072\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"4cd4be6952\",\n" +
                "                \"server\": \"982\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (149)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030521972\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"8dfa460bb8\",\n" +
                "                \"server\": \"959\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (150)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030521712\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"5ff7589b71\",\n" +
                "                \"server\": \"831\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (151)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030521462\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"2445784287\",\n" +
                "                \"server\": \"907\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (152)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"42030521472\",\n" +
                "                \"owner\": \"57721068@N08\",\n" +
                "                \"secret\": \"7c23085420\",\n" +
                "                \"server\": \"826\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"REMEMBERING MOM ... (DSC_2242)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"41175734525\",\n" +
                "                \"owner\": \"143998600@N07\",\n" +
                "                \"secret\": \"e5ae205460\",\n" +
                "                \"server\": \"945\",\n" +
                "                \"farm\": 1,\n" +
                "                \"title\": \"love (153)\",\n" +
                "                \"ispublic\": 1,\n" +
                "                \"isfriend\": 0,\n" +
                "                \"isfamily\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"stat\": \"ok\"\n" +
                "}";


        /*ArrayList<Image> imagesList = new ArrayList();
        try {
            JSONObject object = new JSONObject(response);
            JSONObject photos = object.getJSONObject("photos");
            JSONArray photosArray = photos.getJSONArray("photo");
            for(int i = 0; i < photosArray.length(); i++) {
                JSONObject object1 = photosArray.getJSONObject(i);
                Image image = new Image();
                image.setTitle(object1.getString("title"));
                image.setFarmId(Integer.parseInt(object1.getString("farm")));
                image.setId(object1.getString("id"));
                image.setOwnerId(object1.getString("owner"));
                image.setSecret(object1.getString("secret"));
                image.setServerId(object1.getString("server"));
                imagesList.add(image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    private boolean validateInput(String queryText) {
        return AppUtil.isStringEmpty(queryText) ? false : true;
    }

    @Override
    public void onServiceBegin(int taskCode) {
        if(!this.isViewObjectNull()) {
            viewWeakReference.get().showProgress(taskCode);
        }
    }

    @Override
    public void onServiceSuccess(MasterResponse masterResponse, int taskCode) {
        if(!this.isViewObjectNull()) {
            viewWeakReference.get().hideProgress(taskCode);
        }

        SearchPhotoResponse response = (SearchPhotoResponse) masterResponse;
        int totalImages = response.photos.totalPhotos;
        Image[] images = response.photos.images;
        ArrayList<Image> imagesList = new ArrayList(Arrays.asList(images));

        if(!this.isViewObjectNull()) {
            viewWeakReference.get().onFetchPhotosSuccess(imagesList, totalImages);
        }
    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {
        if(!this.isViewObjectNull()) {
            viewWeakReference.get().hideProgress(taskCode);
            viewWeakReference.get().showError(message);
        }
    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

    }

    /**
     * Method to check if weak reference of the view is null. Usable only from this Presenter.
     * @return true if {@link #viewWeakReference} is null, false otherwise
     */
    private boolean isViewObjectNull() {
        if(viewWeakReference == null) {
            return true;
        } else {
            if(viewWeakReference.get() == null) {
                return true;
            } else {
                return false;
            }
        }
    }

}
