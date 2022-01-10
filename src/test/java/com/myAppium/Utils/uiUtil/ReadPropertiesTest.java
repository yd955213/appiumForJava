package com.myAppium.Utils.uiUtil;

import com.myAppium.Utils.commm.ReadProperties;
import com.myAppium.entity.properties.Appium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Disabled
class ReadPropertiesTest {
    @Autowired
    Appium appium;

    ReadProperties properties = null;
    @BeforeEach
    void setUp() {
        properties = new ReadProperties("/appium.properties");
    }

    @AfterEach
    void tearDown(){
        properties.close();
    }

    @Test
    void getProperty(){
        System.out.println(properties.getProperties());
        System.out.println(Boolean.parseBoolean(""));
        System.out.println(Boolean.parseBoolean(new String()));
        System.out.println(Boolean.parseBoolean("123"));
        System.out.println(Boolean.parseBoolean("%$$%"));

//        System.out.println(properties.getProperty("deviceName1"));
//        System.out.println(properties.getProperty("platformName"));
//        System.out.println(properties.getProperty("platformVersion"));
//        System.out.println(properties.getProperty("appPackage"));
//        System.out.println(properties.getProperty("appActivity"));
//        System.out.println(properties.getProperty("automationName"));
//        System.out.println(Boolean.parseBoolean(properties.getProperty("noReset")));
//
//        System.out.println(Boolean.parseBoolean(properties.getProperty("unicodeKeyboard")));
//        System.out.println(Boolean.parseBoolean(properties.getProperty("resetKeyboard")));
//        System.out.println(Boolean.parseBoolean(properties.getProperty("resetKeyboard")));
//        System.out.println(Boolean.parseBoolean(properties.getProperty("noSign")));
//        System.out.println(properties.getProperty("appiumServerIp"));
//        System.out.println(Integer.parseInt(properties.getProperty("appiumPort")));
    }
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getResourceAsStream() {
        properties.getResourceAsStream("/appium.properties");
    }

    @Test
    void getResourceAsResourceBundle() {
        properties.getResourceAsResourceBundle("appium");
    }

    @Test
    void getAppiumProperties(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        Appium appium = context.getBean(Appium.class);
        System.out.println(appium.getNoSign());
        System.out.println(appium.getDeviceName());
    }
}