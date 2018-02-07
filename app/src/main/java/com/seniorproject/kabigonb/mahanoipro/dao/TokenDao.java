package com.seniorproject.kabigonb.mahanoipro.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LaFezTer on 05-Feb-18.
 */

public class TokenDao {

    @SerializedName("token")     private String token;
    @SerializedName("err")       private String errorMessage;
    @SerializedName("status")    private String statusMessage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
