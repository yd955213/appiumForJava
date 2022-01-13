package com.myAppium.driver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class OkHttpDriverTest {
    String jsonString;
//    String ip = "http://172.168.120.230:19088/DevApi/";
    String ip = "http://172.20.1.185:8090/DevApi/";
    OkHttpDriver okHttpDriver;
    String jsonHeader;
    @BeforeEach
    void setUp() {
        jsonHeader = "{\"Content-Type\":\"application/json;charset=utf-8\", \"Connection\":\"keep-alive\"}";
        jsonString = "{\"DeviceUniqueCode\":\"6D19A9\",\"TimeStamp\":\"2020-04-07 09:48:03\",\"Data\":\"null\"}";
        okHttpDriver = OkHttpDriver.getInstance();
    }

    @Test
    void post() {
        okHttpDriver.addHeader(jsonHeader);
        okHttpDriver.postJSon(ip+ "GetDeviceParams", jsonString);
    }
    @Test
    void post1() {
//        okHttpDriver.addHeader(jsonHeader);
        String s = okHttpDriver.postJSon(ip + "GetDeviceParams", jsonString);
        System.out.println("收到的消息" + s);
    }
    @Test
    void run() throws Exception {
        okHttpDriver.run();
    }

}