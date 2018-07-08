package com.liebersonsantos.almacel.model;

public class Attendants {

    private long id;
    private String attendantName;

    public Attendants() {
    }

    public Attendants(long id, String attendantName) {
        this.id = id;
        this.attendantName = attendantName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttendantName() {
        return attendantName;
    }

    public void setAttendantName(String attendantName) {
        this.attendantName = attendantName;
    }
}
