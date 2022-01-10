package com.myAppium.Utils.commm;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class ReadProperties {
    /**
     * 这个常规的读取配置文件的方法，当配置文件中属性修改时，需要修改代码，例如deviceName 修改为 appium.deviceName
     * getResourceAsStream 未找到对应的值 返回null
     * ResourceBundle 则抛异常
     */
    private Properties properties = null;
    private InputStream inputStream = null;
    public ReadProperties(){}
    public ReadProperties(String filePath){
        getResourceAsStream(filePath);
    }

    public void getResourceAsStream(String filePath){
        properties = new Properties();
        inputStream = this.getClass().getResourceAsStream(filePath);

        if(null == inputStream){
            System.out.println("文件未找到: " + filePath);
            close();
            return;
        }

        try {
            properties.load(inputStream);
            // 没有找到返回空
//            System.out.println(properties.getProperty("deviceName"));
//
//            String noSign = properties.getProperty("noSign");
//            System.out.println("noSign = " + noSign);
        } catch (IOException e) {
            close();
            System.out.println(e.getMessage());
        }finally {
            close(inputStream);
        }
    }

    public void close(){
        if (properties != null) properties.clear();
        if (inputStream != null) close(inputStream);
    }

    /**
     * 当不在 获取 配置文件时，记得调用close() 方法
     * @param key
     * @return
     */
    public String getProperty(@NotNull String key){
        if(properties == null) return null;
//        try {
            return properties.getProperty(key);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
    }
    public Properties getProperties() {
        return properties;
    }

    public void getResourceAsResourceBundle(String fileName){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
        if(resourceBundle == null){
            System.out.println("文件未找到");
            return;
        }

        // MissingResourceException
        String deviceName = resourceBundle.getString("deviceName");
        System.out.println(deviceName);
        Object noSign = resourceBundle.getObject("noSign");
        System.out.println(noSign);
    }

    private void close(InputStream inputStream){
        if(inputStream != null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
