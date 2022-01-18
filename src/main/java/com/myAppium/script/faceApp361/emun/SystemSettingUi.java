package com.myAppium.script.emun;

public enum SystemSettingUi {
    DEVICE_NAME("//*[contains(@text, \"设备名称\")]/following-sibling::*"),
    DEVICE_ID("//*[contains(@text,\"设备ID\")]/following-sibling::*"),
    MAC("//*[contains(@text,\"设备mac\")]/following-sibling::*"),
    VISIT_TYPE("//*[contains(@text,\"访问模式\")]/following-sibling::*"),
    DOMAIN_NAME_TYPE("//*[contains(@text,\"域名\")]"),
    IP_TYPE("//*[contains(@text,\"IP\")]/following-sibling::*"),
    REMOTE_IP_1("com.das.face:id/etIp1"),
    REMOTE_IP_2("com.das.face:id/etIp2"),
    REMOTE_IP_3("com.das.face:id/etIp3"),
    REMOTE_IP_4("com.das.face:id/etIp4"),
    REMOTE_PORT("//*[contains(@text,\"远程端口\")]/following-sibling::*"),
    DEVICE_WORK_TYPE("//*[contains(@text,\"设备模式\")]/following-sibling::*"),
    ID_ONLY_TYPE("//*[contains(@text,\"仅身份识别\")]"),
    GENERAL_TYPE("//*[contains(@text,\"普通门禁\")]"),
    SENIOR_TYPE("//*[contains(@text,\"高级门禁\")]"),
    MODIFY_PASSWORD("//*[contains(@text,\"修改设备密码\")]/following-sibling::*"),
    OLD_PASSWORD("//*[contains(@text,\"原始密码\")]/following-sibling::*"),
    NEW_PASSWORD("//*[contains(@text,\"新密码\")]/following-sibling::*"),
    CONFIRM_PASSWORD("//*[contains(@text,\"确认新密码\")]/following-sibling::*"),
    SAVE_PASSWORD("com.das.face:id/tvRight"),
    BACK("com.das.face:id/ivBack"),
    VERSION("//*[contains(@text,\"版本号\")]/following-sibling::*"),
    ;
    private String value;

    SystemSettingUi(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
