package com.myAppium.entity.properties;

import org.junit.jupiter.api.Test;


class TokenPropertiesTest {

    @Test
    void getProperties(){
        TokenProperties tokenProperties = new TokenProperties();
        System.out.println(tokenProperties.getSecretKeyID());
        System.out.println(tokenProperties.getExpire());
        System.out.println(tokenProperties.getKey());
    }

    @Test
    void getToken() {
        System.out.println(new TokenProperties().getToken());
    }
}