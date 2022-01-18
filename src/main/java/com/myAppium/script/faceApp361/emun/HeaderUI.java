package com.myAppium.script.faceApp361.emun;

public enum HeaderUI {
    SAVE("com.das.face:id/tvRight"),
    BACK("com.das.face:id/ivBack"),
    TITLE("com.das.face:id/tvTitle")
    ;
    /**
     * APP 的 几个界面的保存按钮 回退按钮 都是同一个ui, 这里单独写一个
     */

    private final String value;

    HeaderUI(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
