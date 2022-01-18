package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.ParkApiCase;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkRequest;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
        String jsonString = new JsonUtil().toJsonStringNotNull(listPark, ListParkRequest.class);
        System.out.println(jsonString);

        String responseData = parkApiCase.listPack(jsonString);

        ResponseGson<ListParkResponse> listParkResponse = new JsonUtil().parseObject(responseData, new TypeReference<ResponseGson<ListParkResponse>>(){}.getType());
        System.out.println(listParkResponse);
    }
}