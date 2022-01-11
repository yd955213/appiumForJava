package com.myAppium;

import com.myAppium.DriverFactory.AppiumDriverBuilder;
import com.myAppium.app.AppUtil;
import com.myAppium.entity.properties.Appium;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.myAppium.Utils.commm.AppiumProperties;
import java.util.concurrent.TimeUnit;

public class My02Test {

    Appium appium = new AppiumProperties().getAppium();

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7})
    void test01(int i) throws InterruptedException {

        AppiumDriverBuilder appiumDriverBuilder = new AppiumDriverBuilder(appium.getDeviceName(), appium.getAppPackage(), appium.getAppActivity());
        TimeUnit.SECONDS.sleep(5);
        appiumDriverBuilder.getAndroidDriver().quit();
        System.out.println(i);
    }

    @Test
    void test02() throws InterruptedException {
        AppUtil appUtil = AppUtil.getInstance();
        TimeUnit.SECONDS.sleep(5);
        appUtil.close();
    }
}
