package com.myAppium.script.testCase;

import com.myAppium.app.AppUtil;
import com.myAppium.script.emun.SystemSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilsTest {

    @Test
    void goToSystemSettingUi() {
        new SystemUtils().goToSystemSettingUi();
    }

    @Test
    void goToSystemSettingUi1() {
        Login login = new Login();
        login.login(login.getPassWord());
        AppUtil.getInstance().click(SystemSettings.VOICE_SETTING.getValue());
        new SystemUtils().goToSystemSettingUi();
    }
}