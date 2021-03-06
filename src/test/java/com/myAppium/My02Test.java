package com.myAppium;

import com.alibaba.fastjson.TypeReference;
import com.myAppium.DriverFactory.AppiumDriverBuilder;
import com.myAppium.Utils.commm.JsonUtil;
import com.myAppium.app.AppUtil;
import com.myAppium.app.WebUtil;
import com.myAppium.entity.properties.Appium;
import com.myAppium.script.c3IOT.comm.entity.ResponseGson;
import com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement.api.entity.ListParkResponse;
import com.myAppium.script.c3IOT.login.locationExpression.LoginUI;
import org.apache.commons.collections.ListUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.myAppium.Utils.commm.AppiumProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.ObjectUtils;

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

    @Test
    void test03(){
//        Properties props=System.getProperties(); //获得系统属性集
//        String osName = props.getProperty("os.name"); //操作系统名称
//        String osArch = props.getProperty("os.arch"); //操作系统构架
//        String osVersion = props.getProperty("os.version"); //操作系统版本
//        System.out.println(System.getProperty("os.name"));
//        System.out.println(osName);
//        System.out.println(osArch);
//        System.out.println(osVersion);
        String st = "{\"code\":0,\"msg\":\"ok\",\"data\":{\"list\":[],\"page\":{\"pageNum\":1,\"pageSize\":15,\"pages\":0,\"total\":0}}}";

        ResponseGson<ListParkResponse> object = JsonUtil.parseObject(st, new TypeReference<ResponseGson<ListParkResponse>>() {
        }.getType());
//
        System.out.println(object.getData().getList());
//
        System.out.println(object.getData().getList().isEmpty());

    }

    @Test
    void test04(){
        WebUtil webUtil = new WebUtil();
        webUtil.openBrowser("http://172.168.91.51/login");
        webUtil.waitTitle("C3 IoT");

        int counts = 1000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < counts; i ++){
            WebElement element = webUtil.getWebDriver().findElement(By.xpath(LoginUI.USER_NAME));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("xpath 耗时：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < counts; i ++){
            WebElement element = webUtil.getWebDriver().findElement(By.name("userName"));
        }
        endTime = System.currentTimeMillis();
        System.out.println("By.name 耗时：" + (endTime - startTime));
    }
}
