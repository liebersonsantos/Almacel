package com.liebersonsantos.almacel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Incident implements Parcelable {

    private long id;
    private long attendant;
    private long client;
    private String description;
    private String status;
    private String creationTime;

    public Incident() {
    }

    public Incident(long id, long attendant, long client, String description, String status, String creationTime) {
        this.id = id;
        this.attendant = attendant;
        this.client = client;
        this.description = description;
        this.status = status;
        this.creationTime = creationTime;
    }

    protected Incident(Parcel in) {
        id = in.readLong();
        attendant = in.readLong();
        client = in.readLong();
        description = in.readString();
        status = in.readString();
        creationTime = in.readString();
    }

    public static final Creator<Incident> CREATOR = new Creator<Incident>() {
        @Override
        public Incident createFromParcel(Parcel in) {
            return new Incident(in);
        }

        @Override
        public Incident[] newArray(int size) {
            return new Incident[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAttendant() {
        return attendant;
    }

    public void setAttendant(long attendant) {
        this.attendant = attendant;
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(attendant);
        dest.writeLong(client);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(creationTime);
    }
}
