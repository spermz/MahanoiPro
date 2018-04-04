package com.seniorproject.kabigonb.mahanoipro.manager.http;

import com.seniorproject.kabigonb.mahanoipro.dao.LoginDao;
import com.seniorproject.kabigonb.mahanoipro.dao.LogoutDao;
import com.seniorproject.kabigonb.mahanoipro.dao.RegisterDao;
import com.seniorproject.kabigonb.mahanoipro.dao.ResponseOfferDao;
import com.seniorproject.kabigonb.mahanoipro.dao.ServiceCollectionDao;
import com.seniorproject.kabigonb.mahanoipro.dao.TokenDao;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDao;
import com.seniorproject.kabigonb.mahanoipro.view.WorkListItem;

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

    @POST("logoutProvider")
    Call<TokenDao> providerLogout(@Body LogoutDao logoutDao);

    @POST("providerCheckListOffer")
    Call<ServiceCollectionDao> loadRequestList(@Body ServiceCollectionDao serviceCollectionDao);

    @POST("providerResponseOffer")
    Call<ResponseOfferDao> providerResponseOffer(@Body ResponseOfferDao responseOfferDao);

    @POST("providerShowRequest")
    Call<WorkListDao>  loadWorkList(@Body WorkListDao workListDao);

}
