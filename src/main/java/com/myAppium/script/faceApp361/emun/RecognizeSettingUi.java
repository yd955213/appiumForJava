package com.myAppium.script.faceApp361.emun;

public enum RecognizeSettingUi {

    Similarity("//*[contains(@text,\"相似度阈值\")]/following-sibling::*"),
    smallest_pixel("//*[contains(@text,\"最小像素\")]/following-sibling::*"),
    largest_light("//*[contains(@text,\"最大光照度\")]/following-sibling::*"),
    smallest_light("//*[contains(@text,\"最小光照度\")]/following-sibling::*"),
    Light_contrast("//*[contains(@text,\"光照对比度\")]/following-sibling::*"),


    ALIVE_SWITCH("com.das.face:id/drawableSwitch"),
    ALIVE_SINGLE("com.das.face:id/rbSingleAlive"),
    ALIVE_DOUBLE("com.das.face:id/rbDoubleAlive"),

    ALIVE_VALUE("//*[contains(@text,\"活体阈值\")]/following-sibling::*"),
    WHITE_LIGHT("//*[contains(@text,\"白色补光灯\")]/following-sibling::*"),
    LIGHT_STATUE_CLOSE("//*[contains(@text,\"常关\")]/following-sibling::*"),
    LIGHT_STATUE_OPEN("//*[contains(@text,\"常开\")]/following-sibling::*"),
    LIGHT_STATUE_AUTO("//*[contains(@text,\"自动\")]/following-sibling::*"),
    RECOGNITION_INTERVAL("//*[contains(@text,\"识别间隔\")]/following-sibling::*"),
    WAIT_TIME("//*[contains(@text,\"等待时间\")]/following-sibling::*"),
    ;

    private final String value;

    RecognizeSettingUi(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
