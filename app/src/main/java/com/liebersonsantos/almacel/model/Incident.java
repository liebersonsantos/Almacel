package com.liebersonsantos.almacel.model;

public class Incident {

    private long id;
    private String attendantName;
    private String clientName;
    private String description;
    private String status;
    private String creationTime;

    public Incident() {
    }

    public Incident(long id, String attendantName, String clientName, String description, String status, String creationTime) {
        this.id = id;
        this.attendantName = attendantName;
        this.clientName = clientName;
        this.description = description;
        this.status = status;
        this.creationTime = creationTime;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
}
