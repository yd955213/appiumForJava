package com.myAppium.entity.properties;

public class Appium {
    private String deviceName;
    private String platformName;
    private String platformVersion;
    private String appPackage;
    private String appActivity;
    private Boolean noReset;
    private Boolean unicodeKeyboard;
    private Boolean resetKeyboard;
    private String automationName;
    private Boolean noSign;
    //    private String udId;
    private String appiumServerIp;
    private int appiumPort;

    @Override
    public String toString() {
        return "Appium{" +
                "deviceName='" + deviceName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", appPackage='" + appPackage + '\'' +
                ", appActivity='" + appActivity + '\'' +
                ", noReset=" + noReset +
                ", unicodeKeyboard=" + unicodeKeyboard +
                ", resetKeyboard=" + resetKeyboard +
                ", automationName='" + automationName + '\'' +
                ", noSign=" + noSign +
                ", appiumServerIp='" + appiumServerIp + '\'' +
                ", appiumPort=" + appiumPort +
                '}';
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Appium setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public String getPlatformName() {
        return platformName;
    }

    public Appium setPlatformName(String platformName) {
        this.platformName = platformName;
        return this;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public Appium setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
        return this;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public Appium setAppPackage(String appPackage) {
        this.appPackage = appPackage;
        return this;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public Appium setAppActivity(String appActivity) {
        this.appActivity = appActivity;
        return this;
    }

    public Boolean getNoReset() {
        return noReset;
    }

    public Appium setNoReset(Boolean noReset) {
        this.noReset = noReset;
        return this;
    }

    public Boolean getUnicodeKeyboard() {
        return unicodeKeyboard;
    }

    public Appium setUnicodeKeyboard(Boolean unicodeKeyboard) {
        this.unicodeKeyboard = unicodeKeyboard;
        return this;
    }

    public Boolean getResetKeyboard() {
        return resetKeyboard;
    }

    public Appium setResetKeyboard(Boolean resetKeyboard) {
        this.resetKeyboard = resetKeyboard;
        return this;
    }

    public String getAutomationName() {
        return automationName;
    }

    public Appium setAutomationName(String automationName) {
        this.automationName = automationName;
        return this;
    }

    public Boolean getNoSign() {
        return noSign;
    }

    public Appium setNoSign(Boolean noSign) {
        this.noSign = noSign;
        return this;
    }

    public String getAppiumServerIp() {
        return appiumServerIp;
    }

    public Appium setAppiumServerIp(String appiumServerIp) {
        this.appiumServerIp = appiumServerIp;
        return this;
    }

    public int getAppiumPort() {
        return appiumPort;
    }

    public Appium setAppiumPort(int appiumPort) {
        this.appiumPort = appiumPort;
        return this;
    }
}
