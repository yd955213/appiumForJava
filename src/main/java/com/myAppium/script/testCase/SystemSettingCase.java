package com.myAppium.script.testCase;

import com.myAppium.app.AppUtil;
import com.myAppium.script.emun.HeaderUI;
import com.myAppium.script.emun.SystemSettingUi;
import org.junit.jupiter.api.Assertions;

public class SystemSettingCase {
    AppUtil appUtil = null;
    SystemUtils systemUtils = null;
    private final String titleName = "系统设置";
    private final String saveSuccessToastMsg="保存成功";
    private final String saveFailToastMsg_Ip="IP格式错误";
    private final String deviceNameNotNUll="请设置设备名称";

    public SystemSettingCase(){
        appUtil = new AppUtil();
        systemUtils = new SystemUtils();
        systemUtils.intoSystemSettingUI();
    }

    public void deviceNameCase(String deviceName, String expected){
        sendKeyAndSave(SystemSettingUi.DEVICE_NAME.getValue(), deviceName);
        if(deviceName == null){
            Assertions.assertTrue(appUtil.findElementByWebDriverWait(deviceNameNotNUll));
        }else {
            Assertions.assertTrue(appUtil.findElementByWebDriverWait(saveSuccessToastMsg));
        }
        String assertTest = getAssertTest(deviceName);
        Assertions.assertEquals(expected, assertTest);
    }


    private void sendKeyAndSave(String locationExpression, String value){
        if (!systemUtils.isInExpectUI(HeaderUI.TITLE.getValue(), titleName)) systemUtils.intoSystemSettingUI();
        appUtil.sendKey(locationExpression, value);
        systemUtils.save();
    }

    private String getAssertTest(String locationExpression){
        systemUtils.goToSystemSettingUi();
        systemUtils.intoSystemSettingUI();
        return appUtil.getText(locationExpression);
    }

}
