package com.myAppium.script.testCase;

import com.myAppium.app.AppUtil;
import com.myAppium.script.emun.HeaderUI;
import com.myAppium.script.emun.SystemSettingUi;
import com.myAppium.script.emun.SystemSettings;
import io.appium.java_client.MobileElement;

public class SystemUtils {
    private final AppUtil appUtil;

    public SystemUtils(){
        appUtil = AppUtil.getInstance();
    }

    /**
     * 保存
     */
    public void save(){
        MobileElement element = appUtil.findElement(HeaderUI.SAVE.getValue());
        if(element == null) return;
        element.click();
    }

    /**
     * 文本框输入 value ,并返回 旧的文本值
     * @param locationExpression 表达式
     * @param value 文本值
     * @return oldValue
     */
    public String sendKeyAndSave(String locationExpression, String value){
        String oldValue = appUtil.getText(SystemSettingUi.DEVICE_NAME.getValue());
        appUtil.sendKey(locationExpression, value);
        save();
        return oldValue;
    }

    /**
     * 取消保存
     */
    public void cancel(){
        MobileElement element = appUtil.findElement(HeaderUI.BACK.getValue());
        if(element == null) return;
        element.click();
    }

    /**
     * 系统设置 选择UI 界面：包含 员工管理、通行记录、系统设置、识别设置、输出设置、语音提示等界面
     * 其为输入密码后的第一个页面
     */
    public void goToSystemSettingUi(){
        /*
         当app 不奔溃时，如果每次都要判断是否在 已登录，会导致测试慢，
         */
        Login login = new Login();
        // 为防止登陆失败，理论上这里用 while 最好 懒得改了
        if (!login.isLogon()) {
            // 如果是首页，则输入密码登陆
            login.login(login.getPassWord());
            return;
        }
        appUtil.sleep(100);

        MobileElement element = appUtil.findElement(HeaderUI.TITLE.getValue());
        // 不是登陆之后的UI界面， 点击回退
        int time = 10;
        while(element == null || !element.getText().equals("系统设置")){
            appUtil.click(HeaderUI.BACK.getValue());
            appUtil.sleep(100);
            element = appUtil.findElement(HeaderUI.TITLE.getValue());
            time--;
            // 循环10还未找到，则停止循环
            if (time <= 0){
                break;
            }
        }
//        // 当跳出循环后，此时一定在待机界面，重新登陆即可， 如果有些app 回退10次都没有到主页，加大time次数即可
        if(time <= 0){
            login.login(login.getPassWord());
        }
    }

    public void intoSystemSettingUI(){
        clickOnSystemSettingsUi(SystemSettings.SYSTEM_SETTING.getValue());
    }

    public void intoEmployeeManagementUI(){
        clickOnSystemSettingsUi(SystemSettings.EMPLOYEE_MANAGEMENT.getValue());
    }

    public void intoPassRecordUI(){
        clickOnSystemSettingsUi(SystemSettings.PASS_RECORD.getValue());
    }

    public void intoRecognizeSettingUI(){
        clickOnSystemSettingsUi(SystemSettings.RECOGNIZE_SETTING.getValue());
    }

    public void intoIOSettingUI(){
        clickOnSystemSettingsUi(SystemSettings.IO_SETTING.getValue());
    }

    public void intoVoiceSettingUI(){
        clickOnSystemSettingsUi(SystemSettings.VOICE_SETTING.getValue());
    }

    public void intoSeniorSettingUI(){
        intoSystemSettingUI();
        appUtil.click(SystemSettingUi.VERSION.getValue());
    }

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

    private void clickOnSystemSettingsUi(String locationExpression){
        goToSystemSettingUi();
        appUtil.sleep(100);
        appUtil.click(locationExpression);
    }
}
