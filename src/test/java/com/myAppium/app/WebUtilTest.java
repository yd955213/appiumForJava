package com.myAppium.app;

import com.myAppium.entity.browserType.MyBrowserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebUtilTest {
    WebUtil webUtil;
    @BeforeEach
    void setUp() {
        webUtil = new WebUtil();
    }

    @Test
    void openBrowser() {
    }

    @Test
    void inputByXPath() {
        ////*[contains(@name, "userName")]
        webUtil.openBrowser("http://172.168.91.51/login");
        webUtil.waitTitle("C3");
        webUtil.inputByXPath("//*[contains(@name, \"userName\")]", "admin");
        webUtil.inputByXPath("//*[contains(@name, \"password\")]", "admin123");
        webUtil.clickByXPath("//span[text()=\"登 录\"]");
        webUtil.sleep(200);
        webUtil.clickByXPath("//span[text()=\"100001\"]");
        webUtil.clickByXPath("//span[text()=\"达实物联\"]");
        webUtil.clickByXPath("//span[text()=\"确定\"]");
    }

    @AfterEach
    void tearDown(){
        webUtil.sleep(20*1000);
        webUtil.quit();
    }
}