package com.myAppium.script.c3IOT.login.api.Entity;

import com.alibaba.fastjson.JSONObject;
import com.myAppium.Utils.commm.JsonUtil;

import java.util.*;

public class TokenEntity {
    Client client;
    Token token;

    @Override
    public String toString() {
        return "TokenEntity{" +
                "client=" + client +
                ", token=" + token +
                '}';
    }

    public Client getClient() {
        return client;
    }

    public TokenEntity setClient(Client client) {
        this.client = client;
        return this;
    }

    public Token getToken() {
        return token;
    }

    public TokenEntity setToken(Token token) {
        this.token = token;
        return this;
    }

    public static class Client{
        String type;

        @Override
        public String toString() {
            return "Client{" +
                    "type='" + type + '\'' +
                    '}';
        }

        public String getType() {
            return type;
        }

        public Client setType(String type) {
            this.type = type;
            return this;
        }
    }

    public static class Token{
        Long expire = System.currentTimeMillis() / 1000;
//        Long expire = 1642494214L;
        List<SecretKeyLists> secretKeys;

        @Override
        public String toString() {
            return "Token{" +
                    "expire=" + expire +
                    ", secretKeys=" + secretKeys +
                    '}';
        }

        public Long getExpire() {
            return expire;
        }

        public Token setExpire(Long expire) {
            this.expire = expire;
            return this;
        }

        public List<SecretKeyLists> getSecretKeys() {
            return secretKeys;
        }

        public Token setSecretKeys(List<SecretKeyLists> secretKeys) {
            this.secretKeys = secretKeys;
            return this;
        }
    }

    public static class SecretKeyLists{
        SecretKey secretKey;
        String usage = "payload";

        public String getUsage() {
            return usage;
        }

        public SecretKeyLists setUsage(String usage) {
            this.usage = usage;
            return this;
        }

        public SecretKey getSecretKey() {
            return secretKey;
        }

        public SecretKeyLists setSecretKey(SecretKey secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        @Override
        public String toString() {
            return "SecretKeyLists{" +
                    "secretKey=" + secretKey +
                    ", usage='" + usage + '\'' +
                    '}';
        }
    }


    public static class SecretKey{
        String algorithm = "RSA";
        Properties properties;

        @Override
        public String toString() {
            return "SecretKey{" +
                    "algorithm='" + algorithm + '\'' +
                    ", properties=" + properties +
                    '}';
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public SecretKey setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public Properties getProperties() {
            return properties;
        }

        public SecretKey setProperties(Properties properties) {
            this.properties = properties;
            return this;
        }
    }
    public static class Properties{
        String bitSize = "2048";
        String key = "-----BEGIN RSA PUBLIC KEY-----\n" +
                "MIIBCgKCAQEAgOM6oS1zZsuBNEU0WmrkBsFBO8jptJTZ0k6PovroX4J3hhm4WEP8//hKvehE\n" +
                "ENgfhEPsbNhqd3nVvuKyP/uoo+di7Q8RESFm/N/2xVCEemJ91ErA27fdEYi8d0Dsv/ghynbc\n" +
                "79Y58PkGe3hrkVsd6nMFIDPKTuIkvNYGqb8LBx/51+tKTJdj6iv/lmaM1ifQsr9txR7MzBjp\n" +
                "griKK9MubxcKSjDVKjFBft6UoRb6gJnZ9OaX+lRW9QPTQ3Q7IIddL78FFn1WY/42rKNMyHxr\n" +
                "a/JdPsbKnxsAWf5trm4Zh3JGOvzehBVJodWJPZVGdGgGOGRvDPTaOCG5vkdE4O1YgwIDAQAB\n" +
                "-----END RSA PUBLIC KEY-----\n";

        String keyType = "public";

        @Override
        public String toString() {
            return "Properties{" +
                    "bitSize='" + bitSize + '\'' +
                    ", key='" + key + '\'' +
                    ", keyType='" + keyType + '\'' +
                    '}';
        }

        public String getBitSize() {
            return bitSize;
        }

        public Properties setBitSize(String bitSize) {
            this.bitSize = bitSize;
            return this;
        }

        public String getKey() {
            return key;
        }

        public Properties setKey(String key) {
            this.key = key;
            return this;
        }

        public String getKeyType() {
            return keyType;
        }

        public Properties setKeyType(String keyType) {
            this.keyType = keyType;
            return this;
        }
    }

    public void init(){
        Properties properties = new Properties();

        SecretKey secretKey = new SecretKey();
        secretKey.setProperties(properties);

        SecretKeyLists secretKeyLists = new SecretKeyLists();
        secretKeyLists.setSecretKey(secretKey);

        Token token = new Token();
        token.setSecretKeys(Arrays.asList(secretKeyLists));

        Client client = new Client();
        client.setType("iot-web");

        setToken(token);
        setClient(client);
    }

    public Map toMap(TokenEntity tokenEntity){
//        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.init();
        String jsonString = new JsonUtil().toJsonStringWithNull(tokenEntity, TokenEntity.class);

        return (Map)JSONObject.parseObject(jsonString);
    }
}
