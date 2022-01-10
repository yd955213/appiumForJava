package com.myAppium.app;

import com.myAppium.DriverFactory.AppiumDriverBuilder;
import com.myAppium.Utils.commm.AppiumProperties;
import com.myAppium.entity.properties.Appium;
import io.appium.java_client.android.AndroidDriver;

public class AppDriver {
    private AndroidDriver<?> androidDriver = null;
    private Appium appium = null;
    private AppDriver(){
        initDriver();
    }

    private static class AppDriverHolder{
        private final static AppDriver INSTANCE = new AppDriver();
    }

    public static synchronized AppDriver getInstance(){
        return AppDriverHolder.INSTANCE;
    }

    private void initDriver(){
        appium = new AppiumProperties().getAppium();
        androidDriver = new AppiumDriverBuilder(appium.getDeviceName(), appium.getAppPackage(), appium.getAppActivity())
                .platformVersion(appium.getPlatformVersion())
                .platformName(appium.getPlatformName())
                .automationName(appium.getAutomationName())
                .noReset(appium.getNoReset())
                .resetKeyboard(appium.getResetKeyboard())
                .noSign(appium.getNoSign())
                .appiumServerIp(appium.getAppiumServerIp())
                .appiumPort(appium.getAppiumPort())
                .createAppiumDriver();
    }

    @Override
    public String toString() {
        return "AppDriver{" +
                "androidDriver=" + androidDriver +
                ", appium=" + appium +
                '}';
    }

    public AndroidDriver<?> getAndroidDriver() {
        return androidDriver;
    }
}
