package com.myAppium.Utils.encrypt;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class RSAEncryptTest {

    @Test
    void getKeyPair() throws NoSuchAlgorithmException {
        RSAEncrypt rsaEncrypt = new RSAEncrypt();
        rsaEncrypt.getKeyPair();
    }
}