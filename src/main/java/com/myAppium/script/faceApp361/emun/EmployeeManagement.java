package com.myAppium.script.emun;

public enum EmployeeManagement{
    // 输入文本框
    SEARCH_TEXT("com.das.face:id/etSearchPerson"),
    // 取消按钮
    CANCEL("com.das.face:id/tvCancelSearch"),
    // 搜索按钮
    SEARCH_BUTTON("com.das.face:id/btFilter"),
    // 添加人员按钮
    ADD("com.das.face:id/tvRight"),
    // 点击添加人员， 弹出输入 管理员密码输入框
    PASSWORD("com.das.face:id/password"),
    OPEN_CAMERA("com.das.face:id/circleImageView"),
    PHOTOGRAPH("com.das.face:id/icCamera"),
    CANCEL_PHOTOGRAPH("com.das.face:id/circleImageView"),
    INPUT_NAME("//*[contains(@text,\"姓名\")]/following-sibling::*"),
    INPUT_VIRTUAL("//*[contains(@text,\"虚拟号\")]/following-sibling::*"),
    INPUT_CARD_ID("//*[contains(@text,\"卡号\")]/following-sibling::*")
    ;
    private final String value;

    EmployeeManagement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
