package com.myAppium.script.faceApp361.emun;

public enum HomePage {
    LOGO("com.das.face:id/ivLogo")
    ;
    private String value;

    HomePage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
