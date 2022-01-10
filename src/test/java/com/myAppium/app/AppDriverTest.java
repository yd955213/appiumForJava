package com.myAppium.app;

import com.myAppium.Utils.uiUtil.AutoTools;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppDriverTest {
    @Test
    void getInstance() {
        System.out.println(AppDriver.getInstance().toString());
    }

    @Test
    void getAndroidDriver() {
        Assertions.assertNotNull(AppDriver.getInstance());
    }

    @Test
    @Disabled
    void getSingle(){
        System.out.println(new AutoTools().hashCode());
        System.out.println(new AutoTools().hashCode());
        System.out.println(new AutoTools().hashCode());
        System.out.println(new AutoTools().hashCode());
        System.out.println(new AppUtil().hashCode());
        System.out.println(new AppUtil().hashCode());
        System.out.println(new AppUtil().hashCode());
        System.out.println(new AppUtil().hashCode());
        System.out.println(new AppUtil().hashCode());
        System.out.println(new AppUtil().hashCode());
        System.out.println(new AppUtil().getAndroidDriver().hashCode());
        System.out.println(new AppUtil().getAndroidDriver().hashCode());
        System.out.println(new AppUtil().getAndroidDriver().hashCode());
        System.out.println(new AppUtil().getAndroidDriver().hashCode());
        System.out.println(new AppUtil().getAndroidDriver().hashCode());
    }
}