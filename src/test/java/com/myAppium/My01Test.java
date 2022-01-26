package com.myAppium;

import com.myAppium.app.AppUtil;
import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void test02(){
        AppUtil appUtil = AppUtil.getInstance();
        System.out.println(appUtil.getAndroidDriver().getSessionId());
        appUtil.close();
        try {
            System.out.println(appUtil.getAndroidDriver());
        }catch (Exception e){
            System.out.println("关闭后无法获取");
        }

        try {
            System.out.println(appUtil.getAndroidDriver().getSessionId());
        }catch (Exception e){
            System.out.println("关闭后无法获取");
        }
//.activity.MainActivity  Launcher
//        appUtil.createNewDriver();
//        System.out.println("最后：" + appUtil.getAndroidDriver().getSessionId());
    }

    @Test
    void test03(){
//        AppUtil appUtil = AppUtil.getInstance();
//        MobileElement element;
//        while (true){
//            element = appUtil.findElement("com.das.face:id/ivLogo");
//            if(element != null)
//                System.out.println("test = ");
//        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> list2 = list;
        list2.add(4);

        System.out.println(list.size());
        System.out.println(list2.size());
    }
}
