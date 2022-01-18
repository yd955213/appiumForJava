package com.myAppium.script.faceApp361.testCase;

import com.myAppium.app.AppUtil;
import com.myAppium.script.faceApp361.emun.HeaderUI;
import com.myAppium.script.faceApp361.emun.SystemSettingUi;
import org.junit.jupiter.api.Assertions;
import org.springframework.util.ObjectUtils;

public class SystemSettingCase {
    AppUtil appUtil = null;
    SystemUtils systemUtils = null;
    private final String saveSuccessToastMsg="保存成功";
    private final String saveFailToastMsg_Ip="IP格式错误";
    private final String deviceNameNotNUll="请设置设备名称";

    public SystemSettingCase(){
        appUtil = AppUtil.getInstance();
        systemUtils = new SystemUtils();
    }

    public void deviceNameCase(String deviceName, String expected){
        systemUtils.intoSystemSettingUI();
        String oldDeviceName = systemUtils.sendKeyAndSave(SystemSettingUi.DEVICE_NAME.getValue(), deviceName);
        // 当输入"" 时，保存时提示 请设置设备名称
        if(ObjectUtils.isEmpty(deviceName)){
            Assertions.assertTrue(appUtil.findElementByWebDriverWait(deviceNameNotNUll));
            appUtil.click(HeaderUI.BACK.getValue());
        }else {
            Assertions.assertTrue(appUtil.findElementByWebDriverWait(saveSuccessToastMsg));
        }

        String text = getAssertTestAfterSave(SystemSettingUi.DEVICE_NAME.getValue());
        System.out.println("text = " + text);

        // 保持失败时， 希望 文本值不修该
        if(ObjectUtils.isEmpty(deviceName)){
            expected = oldDeviceName;
        }
        Assertions.assertEquals(expected, text);
    }




    private String getAssertTestAfterSave(String locationExpression){
        systemUtils.goToSystemSettingUi();
        systemUtils.intoSystemSettingUI();
        return appUtil.getText(locationExpression);
    }

    public AppUtil getAppUtil() {
        return appUtil;
    }
}
