package com.seniorproject.kabigonb.mahanoipro.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkListDao implements Parcelable{

    @SerializedName("token")    private String token;
    @SerializedName("providername") private String providerName;
    @SerializedName("err")      private String errorMessage;
    @SerializedName("status")   private String statusMessage;
    @SerializedName("reson")    private String reason;
    @SerializedName("result")    private List<WorkListDataDao> result;

    public WorkListDao() {
    }


    protected WorkListDao(Parcel in) {
        token = in.readString();
        providerName = in.readString();
        errorMessage = in.readString();
        statusMessage = in.readString();
        reason = in.readString();
        result = in.createTypedArrayList(WorkListDataDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeString(providerName);
        dest.writeString(errorMessage);
        dest.writeString(statusMessage);
        dest.writeString(reason);
        dest.writeTypedList(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkListDao> CREATOR = new Creator<WorkListDao>() {
        @Override
        public WorkListDao createFromParcel(Parcel in) {
            return new WorkListDao(in);
        }

        @Override
        public WorkListDao[] newArray(int size) {
            return new WorkListDao[size];
        }
    };

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<WorkListDataDao> getResult() {
        return result;
    }

    public void setResult(List<WorkListDataDao> result) {
        this.result = result;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
