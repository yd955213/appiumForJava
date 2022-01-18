package com.myAppium.script.faceApp361.emun;

public enum IOSettingUi {


    WG_TYPE("//*[contains(@text,\"维根输出类型\")]/following-sibling::*"),
    WG_TYPE_34("//*[contains(@text,\"34\")]/following-sibling::*"),
    WG_TYPE_26("//*[contains(@text,\"26\")]/following-sibling::*"),
    WG_TYPE_NOT_SUPpORT("//*[contains(@text,\"不支持\")]/following-sibling::*"),

    OUT_CARD_TYPE("//*[contains(@text,\"输出卡号\")]/following-sibling::*"),
    OUT_CARD_ID("//*[contains(@text,\"卡号\")]/following-sibling::*"),
    OUT_FACE_ID("//*[contains(@text,\"人脸ID\")]/following-sibling::*"),

    WG_OUT_ORDER("//*[contains(@text,\"维根输出顺序\")]/following-sibling::*"),
    WG_OUT_ORDER_ASC("//*[contains(@text,\"正序\")]/following-sibling::*"),
    WG_OUT_ORDER_DESC("//*[contains(@text,\"反序\")]/following-sibling::*"),

    PULSE_TIME("//*[contains(@text,\"脉冲时间\")]/following-sibling::*"),
    IDENTIFY_SUCCESS("//*[contains(@text,\"识别成功\")]/following-sibling::*"),
    IDENTIFY_FAIL("//*[contains(@text,\"识别失败\")]/following-sibling::*"),

    TIMEOUT_ALARM("//*[contains(@text,\"超时报警时间\")]/following-sibling::*"),
    DETECTION_DOOR_MAGNETIC("//*[contains(@text,\"门磁检测\")]/following-sibling::*"),
    DETECTION_BUTTON("//*[contains(@text,\"按钮检测\")]/following-sibling::*"),
    ;

    private final String value;

    IOSettingUi(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
