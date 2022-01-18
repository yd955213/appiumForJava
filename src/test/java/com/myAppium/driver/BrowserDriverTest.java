package com.myAppium.driver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrowserDriverTest {

    @Test
    void getInstance() {
        BrowserDriver.getInstance();
    }
}