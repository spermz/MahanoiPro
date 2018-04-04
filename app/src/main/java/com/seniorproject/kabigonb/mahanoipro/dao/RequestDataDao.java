package com.seniorproject.kabigonb.mahanoipro.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LaFezTer on 14-Mar-18.
 */

public class RequestDataDao implements Parcelable {
    @SerializedName("_id")              private String id;
    @SerializedName("typeservice")      private int typeService;
    @SerializedName("type_info")        private String typeInfo;
    @SerializedName("moreDetail")       private String MoreDetail;
    @SerializedName("toolsCheck")       private String toolsCheck;
    @SerializedName("problem")          private String problem;
    @SerializedName("placeType")        private String placeType;
    @SerializedName("Username")         private String userName;
    @SerializedName("amount")           private String amount;
    @SerializedName("status")           private int status;

    public RequestDataDao() {

    }

    protected RequestDataDao(Parcel in) {
        id = in.readString();
        typeService = in.readInt();
        typeInfo = in.readString();
        MoreDetail = in.readString();
        toolsCheck = in.readString();
        problem = in.readString();
        placeType = in.readString();
        userName = in.readString();
        amount = in.readString();
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(typeService);
        dest.writeString(typeInfo);
        dest.writeString(MoreDetail);
        dest.writeString(toolsCheck);
        dest.writeString(problem);
        dest.writeString(placeType);
        dest.writeString(userName);
        dest.writeString(amount);
        dest.writeInt(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RequestDataDao> CREATOR = new Creator<RequestDataDao>() {
        @Override
        public RequestDataDao createFromParcel(Parcel in) {
            return new RequestDataDao(in);
        }

        @Override
        public RequestDataDao[] newArray(int size) {
            return new RequestDataDao[size];
        }
    };

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
