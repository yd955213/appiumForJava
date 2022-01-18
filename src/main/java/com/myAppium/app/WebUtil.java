package com.myAppium.app;

import com.myAppium.driver.BrowserDriver;
import com.myAppium.entity.browserType.MyBrowserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebUtil {
    /**
     * webDriver 获取driver 的方法写成了单例模式，测试中如果driver.quit()或者close()后，driver 会为空，请只在UI测试完成后在调用quit()方法
     */
    private WebDriver webDriver;
    private String browserType = MyBrowserType.CHROME;
    public WebUtil(){
        switch (browserType){
            case MyBrowserType.CHROME:
                webDriver = BrowserDriver.getInstance().getWebDriver();
                break;
            case MyBrowserType.FIRE_FOX:
                // 带开发
                break;
            case MyBrowserType.IE:
                // 带开发
                break;
            default:
                webDriver = BrowserDriver.getInstance().getWebDriver();
                break;
        }
    }

    public WebUtil setBrowserType(String browserType) {
        this.browserType = browserType;
        return this;
    }

    public void openBrowser(String url){
        try {
            webDriver.get(url);
        }catch (Exception e){
            System.out.println("打开网址失败： " + e.getMessage());
        }
    }
    //显式等待，等待标题变为指定内容
    public void waitTitle(String expectTitle){
        try {
            //设置一个上限为10秒的显式等待。
            WebDriverWait await=new WebDriverWait(webDriver,10);
            //设置等待条件是标题包含指定内容
            await.until(ExpectedConditions.titleContains(expectTitle));
        } catch (Exception e) {
            System.out.println("显式等待标题变为"+expectTitle+"失败");
            e.printStackTrace();
        }
    }
    public WebElement findElementXpath(String locationExpression) {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.xpath(locationExpression));
        }catch (Exception e){
            System.out.println(String.format("查找元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public WebElement inputByXPath(String locationExpression, String value){
        WebElement element = null;
        try {
            element = findElementXpath(locationExpression);
            element.clear();
            element.sendKeys(value);
        }catch (Exception e){
            System.out.println(String.format("文本框输入值失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public WebElement clickByXPath(String locationExpression){
        WebElement element = null;
        try {
            element = findElementXpath(locationExpression);
            element.click();
        }catch (Exception e){
            System.out.println(String.format("点击元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }
    public String getTextByXPath(String locationExpression){
        String value = null;
        WebElement element;
        try {
            element = findElementXpath(locationExpression);
            value = element.getText();
        }catch (Exception e){
            System.out.println(String.format("点击元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return value;
    }
    public void sleep(int times){
        try {
            TimeUnit.MILLISECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭driver,请在整个UI测试完成后在调用，测试中调用会导致 webDriver 一直为null 直到测试结束；
     */
    public void quit(){
        if(webDriver != null){
            try {
                webDriver.close();
            }catch (Exception e){

            }
        }
    }


}
