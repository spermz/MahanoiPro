package com.seniorproject.kabigonb.mahanoipro.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by LaFezTer on 14-Mar-18.
 */

public class ServiceCollectionDao implements Parcelable {
    @SerializedName("token")        private String token;
    @SerializedName("providername") private String providerName;
    @SerializedName("typeservice")  private int typeService;
    @SerializedName("result") private List<RequestDataDao> requestDataDao;
    @SerializedName("status")       private String statusMessage;
    @SerializedName("err")          private String errorMessage;
    @SerializedName("reason")       private String reason;

    public ServiceCollectionDao() {

    }

    protected ServiceCollectionDao(Parcel in) {
        token = in.readString();
        providerName = in.readString();
        typeService = in.readInt();
        requestDataDao = in.createTypedArrayList(RequestDataDao.CREATOR);
        statusMessage = in.readString();
        errorMessage = in.readString();
        reason = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeString(providerName);
        dest.writeInt(typeService);
        dest.writeTypedList(requestDataDao);
        dest.writeString(statusMessage);
        dest.writeString(errorMessage);
        dest.writeString(reason);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ServiceCollectionDao> CREATOR = new Creator<ServiceCollectionDao>() {
        @Override
        public ServiceCollectionDao createFromParcel(Parcel in) {
            return new ServiceCollectionDao(in);
        }

        @Override
        public ServiceCollectionDao[] newArray(int size) {
            return new ServiceCollectionDao[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
    }

    public List<RequestDataDao> getRequestDataDao() {
        return requestDataDao;
    }

    public void setRequestDataDao(List<RequestDataDao> requestDataDao) {
        this.requestDataDao = requestDataDao;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
