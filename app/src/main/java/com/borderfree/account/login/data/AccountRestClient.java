package com.borderfree.account.login.data;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.entity.StringEntity;

public class AccountRestClient {
    private static final String BASE_URL = "http://10.0.2.2:3000/api/consumerAccounts/";

    private static final AsyncHttpClient client = new AsyncHttpClient();

    public static void loginAccount(Context context, String email, String password, AsyncHttpResponseHandler responseHandler)  {
        try {
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("email", email);
            jsonParams.put("password", password);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(context, getAbsoluteUrl("loginEnvoy"), entity, "application/json", responseHandler);
        } catch (Exception e) {
            responseHandler.onFailure(500, null, Objects.requireNonNull(e.getMessage()).getBytes(), e);
        }
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
