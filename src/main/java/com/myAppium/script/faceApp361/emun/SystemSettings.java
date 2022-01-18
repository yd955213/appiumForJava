package com.myAppium.script.faceApp361.emun;

public enum SystemSettings {
    EMPLOYEE_MANAGEMENT("com.das.face:id/btnEmployeeManagement"),
    PASS_RECORD("com.das.face:id/btnPassRecord"),
    SYSTEM_SETTING("com.das.face:id/btnSystemSetting"),
    RECOGNIZE_SETTING("com.das.face:id/btnRecognizeSetting"),
    IO_SETTING("com.das.face:id/btnIOSetting"),
    VOICE_SETTING("com.das.face:id/btVoiceSetting");

    private final String value;

    SystemSettings(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
