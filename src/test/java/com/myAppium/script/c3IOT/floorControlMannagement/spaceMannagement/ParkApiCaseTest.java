package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkRequest;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkApiCaseTest {
    ParkApiCase parkApiCase;
    @BeforeEach
    void setUp(){
        parkApiCase = new ParkApiCase();
    }

    @Test
    void listPack() {
        ListParkRequest listPark = new ListParkRequest();
        listPark.setKeyword("1");
        listPark.setPageSize(50);
        String jsonString = JsonUtil.toJsonStringNotNull(listPark, ListParkRequest.class);
        System.out.println(jsonString);

        ResponseGson<ListParkResponse> listParkResponse = parkApiCase.listPack(jsonString);
        System.out.println(listParkResponse);
    }
}