package com.myAppium.script.testCase;

import com.myAppium.Utils.commm.ReadProperties;
import com.myAppium.app.AppUtil;
import com.myAppium.script.emun.HeaderUI;
import com.myAppium.script.emun.HomePage;
import com.myAppium.script.emun.SystemSettingUi;
import com.myAppium.script.emun.SystemSettings;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.Assertions;

public class SystemUtils {
    AppUtil appUtil = null;

    public SystemUtils(){
        appUtil = new AppUtil();
    }

    public void save(){
        MobileElement element = appUtil.findElement(HeaderUI.SAVE.getValue());
        if(element == null) return;
        element.click();
    }

    public void cancel(){
        MobileElement element = appUtil.findElement(HeaderUI.BACK.getValue());
        if(element == null) return;
        element.click();
    }

    /**
     * 系统设置 选择UI 界面：包含 员工管理、通行记录、系统设置、识别设置、输出设置、语音提示等界面
     */
    public void goToSystemSettingUi(){
        Login login = new Login();
        if (!login.isLogon()) {
            login.login(login.getPassWord());
            return;
        }

        MobileElement element = appUtil.findElement(HeaderUI.TITLE.getValue());
        if (element == null){
            login.loginOut();
            login.login(login.getPassWord());
            return;
        }

        int time = 10;
        while(!element.getText().equals("系统设置")){
            appUtil.click(HeaderUI.BACK.getValue());
            appUtil.sleep(100);
            element = appUtil.findElement(HeaderUI.TITLE.getValue());
            time--;
            // 循环10还未找到，则停止循环
            if (time <= 0){
                break;
            }
        }
        // 当跳出循环后，此时一定在待机界面，重新登陆即可， 如果有些app 回退10次都没有到主页，加大time次数即可
        if(time <= 0){
            element = appUtil.findElement(HomePage.LOGO.getValue());
            // 这里可能出现app奔溃的情况，获取driver
            if(element == null){
                appUtil.close();
                appUtil=null;
                appUtil = new AppUtil();
            }
            login.login(login.getPassWord());
        }
    }

    public void intoSystemSettingUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(SystemSettings.SYSTEM_SETTING.getValue());
        appUtil.sleep(100);
    };

    public void intoEmployeeManagementUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(SystemSettings.EMPLOYEE_MANAGEMENT.getValue());
        appUtil.sleep(100);
    };

    public void intoPassRecordUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(SystemSettings.PASS_RECORD.getValue());
        appUtil.sleep(100);
    };

    public void intoRecognizeSettingUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(SystemSettings.RECOGNIZE_SETTING.getValue());
        appUtil.sleep(100);
    };

    public void intoIOSettingUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(SystemSettings.IO_SETTING.getValue());
        appUtil.sleep(100);
    };

    public void intoVoiceSettingUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(SystemSettings.VOICE_SETTING.getValue());
        appUtil.sleep(100);
    };

    public void intoSeniorSettingUI(){
        goToSystemSettingUi();
        appUtil.sleep(100);
        intoSystemSettingUI();
        appUtil.sleep(100);
        appUtil.longPress(SystemSettingUi.VERSION.getValue());
        appUtil.sleep(100);
    };

    /**
     * 当前UI 界面是否在期待的UI界面上
     * @param locationExpression 期待的UI界面 的某个元素A的 定位表达式
     * @param elementText 期待的UI界面 的某个元素A的 文本值
     * @return Boolean
     */
    public boolean isInExpectUI(String locationExpression, String elementText){
        int time = 3;
        while (time >0) {
            String text = appUtil.getText(locationExpression);
            if(text.equals(elementText)){
                return true;
            }
            time--;
        }
        return false;
    }

}
