package com.myAppium.script.testCase;

import com.myAppium.Utils.commm.ReadProperties;
import com.myAppium.app.AppUtil;
import com.myAppium.entity.properties.PropertiesLocation;
import com.myAppium.script.emun.HeaderUI;
import com.myAppium.script.emun.HomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Login {
    AppUtil appUtil;
    private String passWord;

    public Login(){
        appUtil = new AppUtil();
        getPassWordForProperties();
    }

    public boolean login(String password){
        if (appUtil.getAndroidDriver() == null) return false;
        AndroidDriver<?> androidDriver = appUtil.getAndroidDriver();
        if (androidDriver == null) return false;
        // 防止第一次启动时，app启动了 缺还在加载主页，这里休息一会
        appUtil.sleep(8000);

        MobileElement element;
        // 弹框 的 需输入的密码框 xpath
        String locationExpression = "com.das.face:id/password";
        int time = 0;
        element = clickAndFindElement(locationExpression);
        while (element == null) {
            element = clickAndFindElement(locationExpression);
            if(element != null) break;
            appUtil.sleep(2000);
            time ++;
            if (time > 10) return false;
        }
        element.sendKeys(password);
        return true;
    }

    /**
     * 通过找首页的logo图标，能找到说明没有登陆 返回false, 反正相反
     * @return boolean
     */
    public boolean isLogon(){
        appUtil.outScreenSave();
        appUtil.sleep(50);
        MobileElement element = appUtil.findElement(HomePage.LOGO.getValue());
        // 'element == null? true: false' can be simplified to 'element == null'
        return element == null;
    }

    /**
     * 循环退出 直到能找到首页logo元素
     */
    public void loginOut(){
        if(isLogon()){
            MobileElement element = appUtil.findElement(HomePage.LOGO.getValue());
            while (element == null){
                appUtil.outScreenSave();
                appUtil.click(HeaderUI.BACK.getValue());
                appUtil.sleep(100);
                element = appUtil.findElement(HomePage.LOGO.getValue());
            }
        }
    }

    private MobileElement clickAndFindElement(String locationExpression){
        appUtil.outScreenSave();
        appUtil.sleep(100);
        // 长按 屏幕右上角 等待弹出密码输入框
        appUtil.longPress(50, 100);
        appUtil.sleep(100);
        return appUtil.findElement(locationExpression);
    }

    private void getPassWordForProperties(){
        ReadProperties readProperties = new ReadProperties(PropertiesLocation.appiumProperties);
        passWord = readProperties.getProperty("passWord");
        readProperties.close();
    }

    public String getPassWord() {
        return passWord;
    }
}
