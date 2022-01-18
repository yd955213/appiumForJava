package com.myAppium.script.c3IOT.login.api;

import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.script.c3IOT.login.api.Entity.TokenEntity;
import org.junit.jupiter.api.Test;

import java.util.Map;

class TokenEntityTest {

    @Test
    void testToString() {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.init();

        String s = new JsonUtil().toJsonStringWithNull(tokenEntity, TokenEntity.class);
        System.out.println(s);
    }

    @Test
    void toMap(){

        TokenEntity tokenEntity = new TokenEntity();
        Map mapJson = tokenEntity.toMap(tokenEntity);

        for (Object map : mapJson.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
        }
    }
}