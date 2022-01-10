package com.myAppium;

import com.myAppium.app.AppUtil;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class My01Test {

    @Test
    void test01(){
        System.out.println((int)Double.parseDouble("3.32"));
        int a = 1;
        int c = 1;
        int b = 12;
        int d= 130;

        int time = 1000;
        String st = "adb shell input swipe %s %s %s %s %s ";
        System.out.println(String.format(st, a, b , c, d, time));

        AppUtil appUtil = null;
//        System.out.println(appUtil.getAndroidDriver());
    }
}
