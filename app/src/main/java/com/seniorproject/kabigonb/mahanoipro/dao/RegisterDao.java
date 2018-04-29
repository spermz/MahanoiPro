package com.seniorproject.kabigonb.mahanoipro.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LaFezTer on 05-Feb-18.
 */

public class RegisterDao {

    @SerializedName("token")        private String token;
    @SerializedName("name")         private String firstname;
    @SerializedName("lastname")     private String lastname;
    @SerializedName("Username")     private String username;
    @SerializedName("email")        private String email;
    @SerializedName("password")     private String password;
    @SerializedName("citizenId")    private String citizenId;
    @SerializedName("Telno")       private String number;
    @SerializedName("detail")       private String detail;
    @SerializedName("typeservice")  private int    typeService;
    @SerializedName("err")       private String errorMessage;
    @SerializedName("status")    private String statusMessage;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
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
