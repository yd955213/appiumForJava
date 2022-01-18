package com.myAppium.script.faceApp361.testCase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class LoginTest {
//    AppUtil appUtil = null;
//    @BeforeEach
//    void setUp() {
//        appUtil = new AppUtil();
//    }

    @Test
    void login() {
        Assertions.assertTrue(new Login().login("123456"));
    }

    @Test
    void getPassWord(){
        System.out.println(new Login().getPassWord());
    }
}