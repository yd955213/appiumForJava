package com.myAppium.Utils.commm;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
public class JsonUtil {

    public String toJsonString(Object bean, Type typeToken){
//        Type type = new TypeToken<>() {}.getType();
        Gson gson = new GsonBuilder().serializeNulls().create();
        String s = null;
        try {
            s = gson.toJson(bean, typeToken);
        }catch (Exception e){
            System.out.println("序列化json失败：" + e.getMessage());
        }
        return s;
    }

    public <T> T parseObject(String jsonStr, Type typeReference){
//        Type type = new TypeReference<T>(){}.getType();
        try {
           return (T) JSON.parseObject(jsonStr, typeReference);
        }catch (Exception e){
            System.out.println("反序列化json失败：" + e.getMessage());
            return null;
        }
    }

}
