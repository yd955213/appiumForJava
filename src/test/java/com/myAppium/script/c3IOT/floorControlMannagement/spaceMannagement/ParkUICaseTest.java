package com.myAppium.script.c3IOT.floorControlMannagement.spaceMannagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParkUICaseTest {

    ParkUICase parkUICase;

    @BeforeEach
    void setUp() {
        parkUICase = new ParkUICase();
    }

    @ParameterizedTest()
    @ValueSource(strings = {"测试", "1"})
    void searchPackByName(String value) {
        parkUICase.searchPackByName(value);
    }

    @Test
    void addParkCase() {
        parkUICase.addParkCase();
    }
}