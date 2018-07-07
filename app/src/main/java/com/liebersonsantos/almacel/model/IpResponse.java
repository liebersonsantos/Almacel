package com.liebersonsantos.almacel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class IpResponse implements Parcelable{

    @SerializedName("ip")
    private String ip;

    public IpResponse() {
    }

    public IpResponse(String ip) {
        this.ip = ip;
    }

    protected IpResponse(Parcel in) {
        ip = in.readString();
    }

    public static final Creator<IpResponse> CREATOR = new Creator<IpResponse>() {
        @Override
        public IpResponse createFromParcel(Parcel in) {
            return new IpResponse(in);
        }

        @Override
        public IpResponse[] newArray(int size) {
            return new IpResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ip);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
