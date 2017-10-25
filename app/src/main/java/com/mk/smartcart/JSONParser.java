package com.mk.smartcart;

import android.support.annotation.NonNull;
import android.util.Log;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;


public class JSONParser {
    /********
     * URLS
     *******/
    private static final String MAIN_URL = "https://anshpurii.000webhostapp.com/jso.php";
    /**
     * TAGs Defined Here...
     */
    public static final String TAG = "TAG";
    /**
     * Key to Send
     */
    private static final String KEY_USER_ID = "user_id";
    /**
     * Response
     */
    private static Response response;
    /**
     * Get Data From WEB
     *
     * @return JSON Object
     */



    public static JSONObject getDataFromWeb(String ItemID) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder().add("itemID",ItemID).build();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(requestBody)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}