package com.myAppium.DriverFactory;

import com.myAppium.app.AppUtil;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

@Disabled
class AndroidDriverTest {
    static AppUtil appUtil = null;
    @BeforeAll
    static void setUp() {
        System.out.println("测试开始");
//        appUtil = new AppUtil(
//                "172.20.1.185:5555",
//                "com.das.face",
//                ".activity.LoadingActivity");
    }

    public void login(){
        if(appUtil.getAndroidDriver() == null)
            return;

        appUtil.longPress(50, 50);
        MobileElement element = appUtil.findElement("com.das.face:id/password");
        while (element == null) {
            appUtil.longPress(50, 50);
            element = appUtil.findElement("com.das.face:id/password");
            if(element != null) break;
            appUtil.sleep(2000);
        }
        element.sendKeys("123456");

    }

    @Test
    void longPress() {
        appUtil.longPress(500,250);
        System.out.println("11111111111");
    }
    @Test
    void longPress1() {
            appUtil.sleep(5000);
            appUtil.longPress(50,50);
            appUtil.sleep(3000);
        System.out.println("222");
    }
    @Test
    void click() {
        Dimension size = appUtil.getAndroidDriver().manage().window().getSize();
        System.out.println(size);
    }

    @Test
    void sendKey() {
        login();
        appUtil.click("com.das.face:id/btnRecognizeSetting");
        appUtil.sendKey("//*[contains(@text, \"最小像素\")]/following-sibling::*", "63");
        appUtil.sleep(3000);
    }

    @AfterAll
    static void tarnDown(){
        appUtil.sleep(3000);
        appUtil.close();
    }
}