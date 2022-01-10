package com.myAppium.script.testCase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SystemSettingCaseTest {
    private static SystemSettingCase systemSettingCase = null;
    @BeforeAll
    static void setUp() {
        systemSettingCase = new SystemSettingCase();
    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @ParameterizedTest
    @CsvSource({"test1, test1","'',''", "^*&^*&, ''", "哈哈, 哈哈"})
    void deviceNameTestCase(String deviceName, String expected) {
        systemSettingCase.deviceNameCase(deviceName, expected);
    }
}