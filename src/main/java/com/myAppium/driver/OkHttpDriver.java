package com.myAppium.driver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okhttp3.internal.Internal;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
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

    public void addHeader(String key, String value){
        headerMap.put(key, value);
        userHeader();
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
     * get 请求
     * @param url
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Headers headers = response.headers();
            for(int i = 0; i < headers.size(); i++) {
                System.out.println(headers.name(i) + ": " + headers.value(i));
            }
            return response.body().string();
        }
    }

    /**
     *  当param中有的值为 null 会抛 空指针异常，后期解决
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, Object> param) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        if (param != null){
            for (Map.Entry<String, Object> entry: param.entrySet()){
                if(null != entry.getValue().toString())
                    builder.addQueryParameter(entry.getKey(), entry.getValue().toString());
            }
//            Set<Map.Entry<String, Object>> entries = param.entrySet();
//            if(null != entries){
//                for (Map.Entry<String, Object> entry: entries){
//                    builder.addQueryParameter(entry.getKey(), entry.getValue().toString());
//                }
//            }
        }

        HttpUrl build = builder.build();
        Request request = new Request.Builder().url(build).build();

        try (Response response = client.newCall(request).execute()) {
            Headers headers = response.headers();
//            for(int i = 0; i < headers.size(); i++) {
//                System.out.println(headers.name(i) + ": " + headers.value(i));
//            }
            return response.body().string();
        }
    }
    public String potRequestPayLoad(String url, String jsonString){
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
        Response response = null;
        String responseString = null;
        int statusCode;
        try {
            response = client.newCall(request).execute();
            statusCode = response.code();
            if (statusCode == 200 || response.isSuccessful()){
            }

            responseString = response.body().string();
            System.out.println("responseString = " + responseString);
        } catch (Exception e) {
//            e.printStackTrace();
            statusCode = 500;
            responseString = "请求异常：错误信息：" + e.getMessage();
        }finally {
            if (response !=null) response.close();
        }

        return responseString;
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
