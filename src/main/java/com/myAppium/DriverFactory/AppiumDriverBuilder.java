package com.myAppium.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumDriverBuilder {
    private final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private String deviceName;
    private String platformName= "Android";
    private String platformVersion = "9";
    private final String appPackage;
    private final String appActivity;
    private Boolean noReset = true;
    private Boolean unicodeKeyboard = false;
    private Boolean resetKeyboard = true;
    private String automationName="UiAutomator2";
    private Boolean noSign = true;
    private String udId;
    private String appiumServerIp="localhost";
    private int appiumPort = 4723;
    private AndroidDriver androidDriver;

    /**
     *  必须调用createAppiumDriver（）方法 否则androidDriver为空
     * @param deviceName
     * @param appPackage
     * @param appActivity
     */
    public AppiumDriverBuilder(String deviceName, String appPackage, String appActivity) {
        this.appPackage = appPackage;
        this.deviceName = deviceName;
        this.appActivity = appActivity;
        this.udId = this.deviceName;
    }

    public AndroidDriver createAppiumDriver(){
        System.out.println("初始化driver");
        androidDriver = null;
        setCapabilities("deviceName", deviceName);
        setCapabilities("platformName", platformName);
        setCapabilities("platformVersion", platformVersion);
        setCapabilities("appPackage", appPackage);
        setCapabilities("appActivity", appActivity);
        setCapabilities("noReset", noReset);
        setCapabilities("unicodeKeyboard", unicodeKeyboard);
        setCapabilities("resetKeyboard", resetKeyboard);
        setCapabilities("automationName", automationName);
        setCapabilities("noSign", noSign);
        setCapabilities("udId", udId);
        setCapabilities("newcommandtimeout", "3600");

        try{
//            System.out.println(desiredCapabilities.toString());
            androidDriver = new AndroidDriver(new URL("http://"+appiumServerIp+":"+appiumPort+"/wd/hub"), desiredCapabilities);

            //隐式等待。
            androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("app启动失败，请检查appium日志信息！" + e.getMessage());
        }
        return androidDriver;
    }

    private void setCapabilities(String capabilityName, Object value){
        if (null != value ){
            desiredCapabilities.setCapability(capabilityName, value);
        }
    }

    public AppiumDriverBuilder platformName(String platformName){
        this.platformName = platformName;
        return this;
    }
    public AppiumDriverBuilder platformVersion(String platformVersion){
        this.platformVersion = platformVersion;
        return this;
    }
    public AppiumDriverBuilder noReset(Boolean noReset){
        this.noReset = noReset;
        return this;
    }
    public AppiumDriverBuilder unicodeKeyboardTrue(){
        this.unicodeKeyboard = true;
        return this;
    }
    public AppiumDriverBuilder resetKeyboard(Boolean resetKeyboard){
        this.resetKeyboard = resetKeyboard;
        return this;
    }
    public AppiumDriverBuilder automationName(String automationName){
        this.automationName = automationName;
        return this;
    }
    public AppiumDriverBuilder noSign(Boolean noSign){
        this.noSign = noSign;
        return this;
    }
    public AppiumDriverBuilder appiumServerIp(String appiumServerIp){
        this.appiumServerIp = appiumServerIp;
        return this;
    }
    public AppiumDriverBuilder udId(String udId){
        if (udId != null || udId.length() > 0) this.udId = udId;
        return this;
    }
    public AppiumDriverBuilder appiumPort(int appiumPort){
        if (appiumPort !=0 ) this.appiumPort = appiumPort;
        return this;
    }



    public AndroidDriver getAndroidDriver() {
        return androidDriver;
    }
}
