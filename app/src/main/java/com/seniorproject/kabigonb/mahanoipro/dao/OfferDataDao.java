package com.seniorproject.kabigonb.mahanoipro.dao;

import com.google.gson.annotations.SerializedName;

public class OfferDataDao {

    @SerializedName("token")            private String token;
    @SerializedName("err")              private String errorMessage;
    @SerializedName("status")           private String statusMessage;
    @SerializedName("providername")     private String providerName;
    @SerializedName("offerId")          private String offerId;

    @SerializedName("_id")              private String id;
    @SerializedName("typeservice")      private int typeService;
    @SerializedName("type_info")        private String typeInfo;
    @SerializedName("moreDetail")       private String MoreDetail;
    @SerializedName("toolsCheck")       private String toolsCheck;
    @SerializedName("problem")          private String problem;
    @SerializedName("placeType")        private String placeType;
    @SerializedName("Username")         private String userName;
    @SerializedName("amount")           private String amount;

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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getMoreDetail() {
        return MoreDetail;
    }

    public void setMoreDetail(String moreDetail) {
        MoreDetail = moreDetail;
    }

    public String getToolsCheck() {
        return toolsCheck;
    }

    public void setToolsCheck(String toolsCheck) {
        this.toolsCheck = toolsCheck;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
