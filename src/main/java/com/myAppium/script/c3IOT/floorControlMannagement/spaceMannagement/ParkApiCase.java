package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.driver.OkHttpDriver;
import com.myAppium.entity.properties.TokenProperties;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkRequest;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkResponse;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.interfaces.ParkUrl;

import java.io.IOException;
import java.util.Map;

public class ParkApiCase {
    OkHttpDriver okHttpDriver;
    String token;

    public ParkApiCase(){
        okHttpDriver = OkHttpDriver.getInstance();
        token = new TokenProperties().getToken();
        okHttpDriver.addHeader("token", token);
    }
    public ResponseGson<ListParkResponse> listPack(String paramJson){

        Map<String, Object> map = (Map) JSONObject.parseObject(paramJson);
        try {

            String responseData = okHttpDriver.get(ParkUrl.listPack, map);
            ResponseGson<ListParkResponse> listParkResponse = JsonUtil.parseObject(responseData, new TypeReference<ResponseGson<ListParkResponse>>() {
            }.getType());
            return listParkResponse;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResponseGson<ListParkResponse> listPack(String parkName, Integer pageNum, Integer pageSize){
        ListParkRequest listParkRequest = new ListParkRequest();
        listParkRequest.setPageNum(pageNum);
        listParkRequest.setPageSize(pageSize);
        listParkRequest.setKeyword(parkName);
        return listPack(listParkRequest);
    }

    public ResponseGson<ListParkResponse> listPack(ListParkRequest listParkRequest){
        return listPack(JsonUtil.toJsonStringNotNull(listParkRequest, ListParkRequest.class));
    }
}
