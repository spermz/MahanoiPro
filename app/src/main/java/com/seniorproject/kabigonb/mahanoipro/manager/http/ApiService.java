package com.seniorproject.kabigonb.mahanoipro.manager.http;

import com.seniorproject.kabigonb.mahanoipro.dao.LoginDao;
import com.seniorproject.kabigonb.mahanoipro.dao.RegisterDao;
import com.seniorproject.kabigonb.mahanoipro.dao.TokenDao;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LaFezTer on 05-Feb-18.
 */

public interface ApiService {

    @POST("registerProvider")
    Call<RegisterDao> registerProvider(@Body RegisterDao registerDao);

    @POST("loginProvider")
    Call<TokenDao> providerLogin(@Body LoginDao loginDao);

}
