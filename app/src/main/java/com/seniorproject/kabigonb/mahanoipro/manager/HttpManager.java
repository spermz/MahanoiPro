package com.seniorproject.kabigonb.mahanoipro.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://161.246.5.206/api/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
