package com.myAppium.script.c3IOT.login.api;

import org.junit.jupiter.api.Test;

class LoginTest {

    @Test
    void getToken() {
        System.out.println(new Login().getToken());
    }

    @Test
    void getDetailByToken() {
        System.out.println(new Login().getDetailByToken());
    }
}