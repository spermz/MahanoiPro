package com.seniorproject.kabigonb.mahanoipro.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LaFezTer on 22-Mar-18.
 */

public class ResponseOfferDao {

    @SerializedName("offerId")      private String offerId;
    @SerializedName("providername") private String providerName;
    @SerializedName("result")       private  String result;
    @SerializedName("err")          private String errorMessage;
    @SerializedName("status")       private String status;
    @SerializedName("token")        private String token;
    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
