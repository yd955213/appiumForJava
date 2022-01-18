package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity;

public class ParkId {
    String id;

    @Override
    public String toString() {
        return "ParkId{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public ParkId setId(String id) {
        this.id = id;
        return this;
    }
}
