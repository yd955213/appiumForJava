package com.myAppium.Utils.commm;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
public class JsonUtil {

    public static String toJsonStringWithNull(Object bean, Type typeToken){
//        Type type = new TypeToken<>() {}.getType();
        Gson gson = new GsonBuilder().serializeNulls().create();
        return toJsonString(gson, bean, typeToken);
    }
    public static String toJsonStringNotNull(Object bean, Type typeToken){
//        Type type = new TypeToken<>() {}.getType();
        Gson gson = new Gson();
        return toJsonString(gson, bean, typeToken);
    }
    private static String toJsonString(Gson gson, Object bean, Type typeToken){
        String s = null;
        try {
            s = gson.toJson(bean, typeToken);
        }catch (Exception e){
            System.out.println("序列化json失败：" + e.getMessage());
        }
        return s;
    }

    public static  <T> T parseObject(String jsonStr, Type typeReference){
//        Type type = new TypeReference<T>(){}.getType();
        try {
           return (T) JSON.parseObject(jsonStr, typeReference);
        }catch (Exception e){
            System.out.println("反序列化json失败：" + e.getMessage());
            return null;
        }
    }

}
