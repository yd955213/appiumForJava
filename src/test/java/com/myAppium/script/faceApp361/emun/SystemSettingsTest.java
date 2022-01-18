package com.myAppium.script.faceApp361.emun;

import org.junit.jupiter.api.Test;

class SystemSettingsTest {
    @Test
    void getValue(){
        System.out.println(SystemSettings.SYSTEM_SETTING);
        System.out.println(SystemSettings.SYSTEM_SETTING.getValue());
    }
}