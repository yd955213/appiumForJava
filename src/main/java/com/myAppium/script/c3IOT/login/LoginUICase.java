package com.myAppium.script.c3IOT.login;

import com.myAppium.app.WebUtil;
import com.myAppium.script.c3IOT.floorControlMannagement.header.HeaderUI;
import com.myAppium.script.c3IOT.login.api.interfaces.LoginUrl;
import com.myAppium.script.c3IOT.login.locationExpression.LoginUI;
import org.openqa.selenium.WebElement;

public class LoginUICase {
    private static boolean isLogin = false;
    WebUtil webUtil;
    public LoginUICase(){
        webUtil = new WebUtil();
    }
    public void login(){
        webUtil.openBrowser(LoginUrl.LOGIN_URL);
        webUtil.waitTitle("C3");
        webUtil.inputByXPath(LoginUI.USER_NAME, "admin");
        webUtil.inputByXPath(LoginUI.PASS_WORD, "admin123");
        webUtil.clickByXPath(LoginUI.LOGIN_BUTTON);
        webUtil.sleep(200);
        webUtil.clickByXPath(LoginUI.TENANT_RADIO_2);
        webUtil.clickByXPath(LoginUI.TENANT_RADIO_1);
        webUtil.clickByXPath(LoginUI.CONFORM_TENANT);
        webUtil.sleep(1);
        //能找到用户管理组 界面 则登陆成功
        WebElement element = webUtil.findElementXpath(HeaderUI.userItem);
        if (null != element )
            isLogin = true;
    }

    public void logout(){
        webUtil.clickByXPath(HeaderUI.userItem);
        WebElement element = webUtil.clickByXPath(HeaderUI.logOut);
        if (null != element )
            isLogin = false;
    }

    public static boolean isIsLogin() {
        return isLogin;
    }
}
