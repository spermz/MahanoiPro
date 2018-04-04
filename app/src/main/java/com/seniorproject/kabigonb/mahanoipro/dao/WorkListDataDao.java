package com.seniorproject.kabigonb.mahanoipro.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WorkListDataDao implements Parcelable{

    @SerializedName("token")    private String token;
    @SerializedName("err")      private String errorMessage;
    @SerializedName("status")   private String statusMessage;
    @SerializedName("Username") private String userName;
    @SerializedName("Providername") private String providerName;
    @SerializedName("offerId")  private String offerId;
    @SerializedName("latitude") private Double latitude;
    @SerializedName("longitude")    private Double longitude;
    @SerializedName("typeservice")  private int typeService;

    public WorkListDataDao() {}


    protected WorkListDataDao(Parcel in) {
        token = in.readString();
        errorMessage = in.readString();
        statusMessage = in.readString();
        userName = in.readString();
        providerName = in.readString();
        offerId = in.readString();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        typeService = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeString(errorMessage);
        dest.writeString(statusMessage);
        dest.writeString(userName);
        dest.writeString(providerName);
        dest.writeString(offerId);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeInt(typeService);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkListDataDao> CREATOR = new Creator<WorkListDataDao>() {
        @Override
        public WorkListDataDao createFromParcel(Parcel in) {
            return new WorkListDataDao(in);
        }

        @Override
        public WorkListDataDao[] newArray(int size) {
            return new WorkListDataDao[size];
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
    }
}
