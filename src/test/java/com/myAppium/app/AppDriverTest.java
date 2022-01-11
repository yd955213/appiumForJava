package com.myAppium.app;

import com.myAppium.Utils.uiUtil.AutoTools;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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