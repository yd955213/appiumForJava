package com.myAppium.driver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okhttp3.internal.Internal;
import org.apache.commons.collections4.map.HashedMap;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpDriver {

    private boolean isUserHeader = false;
    private boolean isUserCookie = false;
    private Map<String, String > headerMap;

    private OkHttpClient client;

    private OkHttpDriver(){
        client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .build();
        headerMap = new HashedMap<>();
    }

    private static class OKHttpDriverHolder{
        private final static OkHttpDriver INSTANCE = new OkHttpDriver();
    }

    public static OkHttpDriver getInstance(){
        return OKHttpDriverHolder.INSTANCE;
    }

    /**
     * 添加Http 信息管理头
     * @param jsonString
     */
    public void addHeader(String jsonString){
        try {
            JSONObject jsonObject = JSON.parseObject(jsonString);
            for(String key: jsonObject.keySet()){
                headerMap.put(key, jsonObject.getString(key));
            }
            userHeader();
        }catch (Exception e){
            System.out.println("添加http信息头失败，格式话json出错，请检查json格式！" + jsonString);
        }
    }

    public void removeHeader(String key){
        if(headerMap != null) headerMap.remove(key);
    }

    public void clearHeader(){
        headerMap = new HashedMap<>();
    }

    public void userHeader(){
        isUserHeader = true;
    }

    /**
     * 发送json 格式数据
     * @param url
     * @param jsonString
     * @return
     */
    public String postJSon(String url, String jsonString){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonString, mediaType);
        Request.Builder newBuilder = new Request.Builder();
        if(isUserHeader){
            for(String key: headerMap.keySet()){
                newBuilder.addHeader(key, headerMap.get(key));
            }
        }

        Request request =
                newBuilder.url(url)
                        .post(body)
                        .build();

//        Headers headers = request.headers();
//        for (int i = 0; i < headers.size(); i++) {
//            System.out.println(headers.name(i) + ": " + headers.value(i));
//        }
        try {
            Response response = client.newCall(request).execute();

            String string = response.body().string();
            System.out.println("response = " + string);

            return string;
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("http 连接超时（OkHttpDriver）："+e.getMessage());
            return null;
        }
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());
        }
    }
}
