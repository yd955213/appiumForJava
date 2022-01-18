package com.myAppium.Utils.encrypt;

import org.apache.commons.collections4.map.HashedMap;
import org.bouncycastle.util.encoders.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class RSAEncrypt {
    private Map<String, String> keyMap = new HashedMap<>();

    public RSAEncrypt(){
        try {
            getKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPrivateKey aPrivate = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
        String privateKey = new String(Base64.encode(aPrivate.getEncoded()));
        String publicKey = new String(Base64.encode(aPublic.getEncoded()));

        keyMap.put("publicKey", publicKey);
        keyMap.put("privateKey", privateKey);
    }

    public Map<String, String> getKeyMap() {
        return keyMap;
    }
}
