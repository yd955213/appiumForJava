package com.myAppium.script.c3IOT.login.locationExpression;

import com.myAppium.script.c3IOT.comm.entity.WebUrl;

public interface LoginUI {
    String USER_NAME = "//*[contains(@name, \"userName\")]";
    String PASS_WORD = "//*[contains(@name, \"password\")]";
    String LOGIN_BUTTON = "//span[text()=\"登 录\"]";
    String TENANT_RADIO_1 = "//span[text()=\"达实物联\"]";
    String TENANT_RADIO_2 = "//span[text()=\"100001\"]";
    String CONFORM_TENANT = "//span[text()=\"确定\"]";
    String CANCEL_TENANT = "//span[text()=\"取消\"]";
}
