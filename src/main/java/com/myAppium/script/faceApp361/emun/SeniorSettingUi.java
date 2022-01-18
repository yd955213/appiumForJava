package com.myAppium.script.faceApp361.emun;

public enum SeniorSettingUi {
    AUTO_REBOOT("//*[contains(@text,\"自动重启\")]/following-sibling::*"),
    REBOOT_CYCLE("//*[contains(@text,\"重启周期\")]/following-sibling::*"),
    REBOOT_TIME("//*[contains(@text,\"重启时间\")]/following-sibling::*"),
    QR_CODE("//*[contains(@text,\"二维码识别\")]/following-sibling::*"),
    FACE_AND_CARD("//*[contains(@text,\"卡加人脸\")]/following-sibling::*"),
    BRUSH_CARD("//*[contains(@text,\"刷卡\")]/following-sibling::*"),
    CALL_LIFT("//*[contains(@text,\"呼梯\")]/following-sibling::*"),
    OPEN_DOOR_BY_SUPPER_PASSWORD("//*[contains(@text,\"超级密码开门\")]/following-sibling::*"),
    SCREEN_SAVER("//*[contains(@text,\"屏保\")]/following-sibling::*"),
    SAVE_ERROR_PHOTO("//*[contains(@text,\"保存错误照片\")]/following-sibling::*"),
    DEBUG("//*[contains(@text,\"调试模式\")]/following-sibling::*"),
    HARDWARE_DETECTION("//*[contains(@text,\"硬件检测\")]/following-sibling::*"),
    SHOW_IP("//*[contains(@text,\"显示IP\")]/following-sibling::*"),
    CLEAR_EMPLOYEE("//*[contains(@text,\"清理人员\")]/following-sibling::*"),
    BOUNCED_LEFT_BUTTON("com.das.face:id/btLeft"),
    BOUNCED_RIGHT_BUTTON("com.das.face:id/btRight"),
    CLEAR_RECORD("//*[contains(@text,\"清理记录\")]/following-sibling::*"),
    CLEAR_LOG("//*[contains(@text,\"清理日志\")]/following-sibling::*"),
    BULK_INPUT("//*[contains(@text,\"批量导入\")]/following-sibling::*"),
    // 人脸框设置： 显示人脸框
    FACE_RECT_SHOW("com.das.face:id/rbShowFaceRect"),
    // 人脸框设置： 隐藏人脸框
    FACE_RECT_NOT_SHOW("com.das.face:id/rbHideFaceRect"),
    // 识别信息显示： 姓名
    INFO_NAME("com.das.face:id/cbName"),
    // 识别信息显示： 工号
    INFO_EMPLOYEE_NUMBER("com.das.face:id/cbEmpNo"),
    // 识别信息显示： 部门
    INFO_DEPARTMENT("com.das.face:id/cbDepartment"),
    ;

    private final String value;

    SeniorSettingUi(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
