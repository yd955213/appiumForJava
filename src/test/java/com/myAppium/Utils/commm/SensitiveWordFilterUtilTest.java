package com.myAppium.Utils.commm;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

class SensitiveWordFilterUtilTest {

    @Test
    void contains() {
    }

    @Test
    void getSensitiveWord() throws IOException {
        ClassPathResource classpathResource = new ClassPathResource("static/sensitiveWord/敏感词库表统计.xlsx");
        System.out.println(classpathResource.getFile().getPath());
    }

    @Test
    void checkTxt() {
        System.out.println(SensitiveWordFilterUtil.findSensitiveWordList("卖淫1嫖娼1杀人犯1法共产党国民党法轮大法"));
    }

    @Test
    void getRandomSensitiveWord() {
        for (int i = 0; i < 100; i++){
            System.out.println(SensitiveWordFilterUtil.getRandomSensitiveWord());
        }

    }

//    @Test
//    void initSensitiveWordMap() {
//        Set<String> set = new HashSet<>();
//        set.add("卖淫杀人犯");
//        set.add("卖淫");
//        set.add("嫖娼");
//        set.add("卖淫法轮大法");
////        for ()
//
//        SensitiveWordFilterUtil.initSensitiveWordMap(set);
//        System.out.println(SensitiveWordFilterUtil.getSensitiveWordMap());
//        System.out.println("*******");
////        System.out.println(SensitiveWordFilterUtil.getSensitiveWordMap().get("卖淫"));
////        System.out.println(SensitiveWordFilterUtil.getSensitiveWordMap().get("嫖"));
////        System.out.println(SensitiveWordFilterUtil.getSensitiveWordMap().get("卖"));
//        System.out.println(SensitiveWordFilterUtil.findSensitiveWordList("卖淫是法国啊卖淫嫖娼杀人犯法共产党国民党卖淫法轮大法"));
//////        System.out.println(SensitiveWordFilterUtil.findSensitiveWordList("卖淫嫖娼卖淫卖淫卖淫"));
//    }


}