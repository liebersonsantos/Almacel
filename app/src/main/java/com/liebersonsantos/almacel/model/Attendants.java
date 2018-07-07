package com.liebersonsantos.almacel.model;

public class Attendants {

    private int id;
    private String attendantName;

    public Attendants() {
    }

    public Attendants(int id, String attendantName) {
        this.id = id;
        this.attendantName = attendantName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttendantName() {
        return attendantName;
    }

    public void setAttendantName(String attendantName) {
        this.attendantName = attendantName;
    }
}
