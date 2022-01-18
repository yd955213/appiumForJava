package com.myAppium.script.c3IOT.login.api;

import com.alibaba.fastjson.TypeReference;
import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.Utils.encrypt.RSAEncrypt;
import com.myAppium.driver.OkHttpDriver;
import com.myAppium.entity.properties.TokenProperties;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.login.api.interfaces.LoginUrl;
import com.myAppium.script.c3IOT.login.api.Entity.TokenEntity;

import java.util.UUID;

public class Login {
    TokenEntity tokenEntity;
//    String secretKeyId;
//    String key;
//    Long expire;
    TokenProperties tokenProperties;
    OkHttpDriver okHttpDriver;
    public Login(){
        tokenProperties = new TokenProperties();
        okHttpDriver = OkHttpDriver.getInstance();
    }

    public String getToken(){
        String url = LoginUrl.token;
        okHttpDriver.addHeader("SecretKeyId", tokenProperties.getSecretKeyID());
        okHttpDriver.addHeader("transID", UUID.randomUUID().toString());
        okHttpDriver.addHeader("EncryptType", "none");

        tokenEntity = new TokenEntity();
        tokenEntity.init();

        RSAEncrypt rsaEncrypt = new RSAEncrypt();
        tokenEntity.getToken().getSecretKeys().get(0).getSecretKey().getProperties().setKey(rsaEncrypt.getKeyMap().get("publicKey"));
        String jsonString = new JsonUtil().toJsonStringWithNull(tokenEntity, TokenEntity.class);
        System.out.println(jsonString);
        String requestData = okHttpDriver.potRequestPayLoad(url, jsonString);

        ResponseGson<String> responseGson = new JsonUtil().parseObject(requestData, new TypeReference<ResponseGson<String>>() {
        }.getType());

        return responseGson.getData();
    }
    // http://172.168.91.51/admin/system/api/User/GetDetailByToken
    public String getDetailByToken(){
        String token = getToken();

        okHttpDriver.addHeader("token", token);
        String s = okHttpDriver.postJSon(LoginUrl.getDetailByToken, "");
        return s;
    }
}
