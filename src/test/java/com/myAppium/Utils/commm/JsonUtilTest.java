package com.myAppium.Utils.commm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.util.Beta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

class JsonUtilTest {
    JsonUtil jsonUtil;
    /**
     * 1、性能上来说，fastjson 强于 gson
     * 2、从功能或者可控的细腻度来说，gson 强于 fastjson
     * 建议混用：Gson将bean转换json字符串确保数据的正确，使用FastJson将字符串Json转换Bean。兼顾性能和准确性
     */
    User user;
    RequestFermat<User> requestFermat;
    String testJsonStr = "{\"code\":\"200\",\"data\":{\"age\":20,\"name\":\"test\",\"gender\":\"男\"},\"msg\":\"OK\"}";

    Gson gson;

    @BeforeEach
    void setUp(){
        jsonUtil = new JsonUtil();
        
        user = new User();
        user.setAge(20);
        user.setName("test");

        requestFermat = new RequestFermat<>();
        requestFermat.setCode("200");
        requestFermat.setMsg("OK");
        requestFermat.setData(user);
    }

    @Test
    void fastjsonTest(){
        System.out.println(JSON.toJSONString(requestFermat));
        RequestFermat<User> requestFermat = JSON.parseObject(testJsonStr, new TypeReference<RequestFermat<User>>(){}.getType());
        System.out.println(requestFermat);
    }

    @Test
    void gsonTest(){
        gson = new GsonBuilder().serializeNulls().create();
        String toJson = gson.toJson(requestFermat, new TypeToken<RequestFermat<User>>(){}.getType());
        System.out.println(toJson);

        // gson 区分大小写
        RequestFermat<User> fromJson = gson.fromJson(testJsonStr, new TypeToken<RequestFermat<User>>() {}.getType());
        System.out.println(fromJson);
    }

    @Test
    void toJsonPerformanceTest(){

        int times = 5000000;
        long startTime = System.currentTimeMillis();
        for (int i=0; i < times; i++){
            JSON.toJSONString(requestFermat);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("fastjson 耗时" + (startTime - endTime));

        Type type = new TypeToken<RequestFermat<User>>() {}.getType();
        gson = new Gson();
        startTime = System.currentTimeMillis();
        for (int i=0; i < times; i++){
            gson.toJson(requestFermat, type);
        }
        endTime = System.currentTimeMillis();

        System.out.println("gson 耗时" + (startTime - endTime));

        /*
        fastjson 耗时-2699
        gson 耗时-3840
         */
    }

    @Test
    void formJsonPerformanceTest(){
        Type type = new TypeReference<RequestFermat<User>>() {}.getType();
        int times = 5000000;
        long startTime = System.currentTimeMillis();
        for (int i=0; i < times; i++){
            JSON.parseObject(testJsonStr, type);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("fastjson 耗时" + (startTime - endTime));

        Type typeToken = new TypeToken<RequestFermat<User>>() {}.getType();
        gson = new Gson();
        startTime = System.currentTimeMillis();
        for (int i=0; i < times; i++){
            gson.fromJson(testJsonStr, typeToken);
        }
        endTime = System.currentTimeMillis();

        System.out.println("gson 耗时" + (startTime - endTime));
        /*
        fastjson 耗时-3580
        gson 耗时-4170
         */
    }
    @Test
    void toJsonString() {

        Type typeToken = new TypeToken<RequestFermat<User>>(){}.getType();
        System.out.println(jsonUtil.toJsonString(requestFermat, typeToken));
    }

    @Test
    void parseObject() {
        Type type = new TypeReference<RequestFermat<User>>(){}.getType();
        RequestFermat<User> object = jsonUtil.parseObject(testJsonStr, type);

        System.out.println(object);
        System.out.println(object.getData());
    }

    @Beta
    private static class RequestFermat<T>{
        String code;
        String msg;
        T data;

        @Override
        public String toString() {
            return "RequestFermat{" +
                    "Code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }

        public String getCode() {
            return code;
        }

        public RequestFermat<T> setCode(String code) {
            this.code = code;
            return this;
        }

        public String getMsg() {
            return msg;
        }

        public RequestFermat<T> setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public T getData() {
            return data;
        }

        public RequestFermat<T> setData(T data) {
            this.data = data;
            return this;
        }
    }

    @Beta
    private static class User{
        String name;
        String gender;
        int age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getGender() {
            return gender;
        }

        public User setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public int getAge() {
            return age;
        }

        public User setAge(int age) {
            this.age = age;
            return this;
        }
    }
}