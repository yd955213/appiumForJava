package com.myAppium.Utils.commm;

import com.myAppium.entity.properties.Appium;
import com.myAppium.entity.properties.PropertiesLocation;

/**
 * 获取 appium 配置文件信息
 */
public class AppiumProperties {
    private Appium appium = null;
    public AppiumProperties(){
        appium = new Appium();
        init();
    }

    private void init(){
        ReadProperties readProperties = new ReadProperties(PropertiesLocation.appiumProperties);
        appium.setDeviceName(readProperties.getProperty("deviceName"));
        appium.setPlatformName(readProperties.getProperty("platformName"));
        appium.setPlatformVersion(readProperties.getProperty("platformVersion"));
        appium.setAppPackage(readProperties.getProperty("appPackage"));
        appium.setAppActivity(readProperties.getProperty("appActivity"));
        appium.setAutomationName(readProperties.getProperty("automationName"));
        appium.setNoReset(Boolean.parseBoolean(readProperties.getProperty("noReset")));
        appium.setUnicodeKeyboard(Boolean.parseBoolean(readProperties.getProperty("unicodeKeyboard")));
        appium.setResetKeyboard(Boolean.parseBoolean(readProperties.getProperty("resetKeyboard")));
        appium.setResetKeyboard(Boolean.parseBoolean(readProperties.getProperty("resetKeyboard")));
        appium.setNoSign(Boolean.parseBoolean(readProperties.getProperty("noSign")));
        appium.setAppiumServerIp(readProperties.getProperty("appiumServerIp"));
        try {
            appium.setAppiumPort(Integer.parseInt(readProperties.getProperty("appiumPort")));
        }catch (NumberFormatException e){
            appium.setAppiumPort(0);
        }

        readProperties.close();
    }

    public Appium getAppium() {
        return appium;
    }
}
