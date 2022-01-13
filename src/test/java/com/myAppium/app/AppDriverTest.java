package com.myAppium.app;

import com.myAppium.driver.AppDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppDriverTest {
    @Test
    void getInstance() {
        System.out.println(AppDriver.getInstance().toString());
    }

    @Test
    void getAndroidDriver() {
        Assertions.assertNotNull(AppDriver.getInstance());
    }

}