package com.myAppium.script.faceApp361.emun;

public enum VoiceSettingUi {

    IDENTIFY_SUCCESS("//*[contains(@text,\"识别成功\")]/following-sibling::*"),
    WORK_TIME("//*[contains(@text,\"上班时间\")]/following-sibling::*"),
    BEFORE_WORK_TIME("//*[contains(@text,\"上班前\")]/following-sibling::*"),
    AFTER_WORK_TIME("//*[contains(@text,\"下班后\")]/following-sibling::*"),
    IDENTIFY_FAIL("//*[contains(@text,\"识别失败\")]/following-sibling::*"),

    ;

    private final String value;

    VoiceSettingUi(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
