package com.myAppium.app;

import com.myAppium.Utils.uiUtil.AutoTools;
import com.myAppium.driver.AppDriver;
import com.myAppium.script.faceApp361.testCase.Login;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppUtil{
    private final List<String> crashActive = Arrays.asList(".Launcher");
    private final List<String> loadingActive = Arrays.asList(".activity.LoadingActivity");
    private final List<String> mainActive = Arrays.asList(".activity.MainActivity");
    private AndroidDriver<?> androidDriver = null;
    private AppUtil(){
        androidDriver = AppDriver.getInstance().getAndroidDriver();
    }
    private static class AppUtilHolder{
        private final static AppUtil INSTANCE = new AppUtil();
    }

    public static synchronized AppUtil getInstance(){
        return AppUtil.AppUtilHolder.INSTANCE;
    }

    /**
     * 长安某个坐标元素
     * @param x x轴坐标
     * @param y y轴坐标
     * @return boolean
     */
    public boolean longPress(int x, int y){
        if(androidDriver == null) createNewDriver();
        try {
            TouchAction<?> touchAction = new TouchAction<>(androidDriver);
            touchAction.longPress(PointOption.point(x, y)).release().perform();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 长按某个元素
     * @param locationExpression 元素定位表达式
     * @return boolean
     */
    public boolean longPress(String locationExpression){
        if(androidDriver == null) createNewDriver();
        MobileElement element = findElement(locationExpression);
        if (element == null) return false;

        try {
            Point center = element.getCenter();
            return longPress(center.x, center.y);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean click(String locationExpression){
        MobileElement element = findElement(locationExpression);

        if (element == null)
            return false;

        element.click();
        return true;
    }
    public boolean click(String x, String y){
        try {
            int x1= AutoTools.stringToInt(x);
            int y1 = AutoTools.stringToInt(y);
            return click(x1, y1);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("点击屏幕失败，坐标点（"+x+", " +y+"）");
            return false;
        }
    }

    public boolean click(int x, int y){

        if(androidDriver == null) createNewDriver();

        try {
            TouchAction<?> touchAction = new TouchAction<>(androidDriver);
            touchAction.press(PointOption.point(x, y)).release().perform();
            return true;
        }catch (Exception e){
//            System.out.println(e.getMessage());
            System.out.println("点击屏幕失败，坐标点（"+x+", " +y+"）");
            return false;
        }
    }

    public String getText(String locationExpression){
        MobileElement element = findElement(locationExpression);

        if (element == null) return null;

        try {
            return element.getText();
        }catch (Exception e){
            return null;
        }
    }

    public void outScreenSave(){
        //防止屏保影响测试 点击退出屏保
//        Dimension screenSize = getScreenSize();
//        int x = screenSize.getWidth()/2;
//        int y = screenSize.getHeight()/2;
        /*
         设备底部没有元素，就点击底部， 这里要注意，如果设备没有进入屏保状态是，此时点击位置有元素，易导致不可控问题
            1、点击后直接进入其他Ui界面，导致用例执行失败
            2、直接报错
         */
//        click(1,screenSize.getHeight());
        click(0,0);
    }

    /**
     * 获取屏幕尺寸， 异常时 返回（0,0）
     * @return Dimension
     */
    public Dimension getScreenSize(){
        try {
            return androidDriver.manage().window().getSize();
        }catch (Exception e){
            return new Dimension(0, 0);
        }
    }

    /**
     * 像文本框输入数据，当输入的value 为 null 返回 false
     * @param locationExpression 元素定位表达式
     * @param value 输入的值
     * @return true, false
     */
    public String sendKey(String locationExpression, String value){
        MobileElement element = findElement(locationExpression);
        if(element == null || value == null )
            return null;
        try {
            element.clear();
            String text = element.getText();
            if(text == null || text.length() <=0){
                element.sendKeys(value);
            }else {
            /*
            当clear()方法失效，使用adb 命令清空文本
             */
                element.click();
                adbClear(element);
                adbText(value);
            }
            value = element.getText();
        }catch (Exception e){
            System.out.println(String.format("文本输入异常：元素定位表达式：%s，预期值：%s。", locationExpression, value));
            value = null;
        }

        return value;
    }

    /**
     * 模拟 滑块滑动
     * @param start_x 屏幕x轴 开始位置
     * @param start_y 屏幕y轴 开始位置
     * @param end_x 屏幕x轴 结束位置
     * @param end_y 屏幕y轴 结束位置
     * @return boolean
     */
    public boolean swipe(int start_x, int start_y, int end_x, int end_y){

        if(androidDriver == null) createNewDriver();
        try {
            TouchAction<?> action = new TouchAction<>(androidDriver);
            action.longPress(PointOption.point(start_x, start_y))
                    .moveTo(PointOption.point(end_x, end_y))
                    .release()
                    .perform();

            return true;
        }catch (Exception e){
            System.out.println("滑动失败");
            return false;
        }
    }

    public boolean swipe(String start_x, String start_y, String end_x, String end_y){
        try {
            int start_x1 = AutoTools.stringToInt(start_x);
            int start_y1 = AutoTools.stringToInt(start_y);
            int end_x1 = AutoTools.stringToInt(end_x);
            int end_y1 = AutoTools.stringToInt(end_y);
            return swipe(start_x1, start_y1, end_x1, end_y1);
        }catch (Exception e){
            System.out.println("滑动失败");
            return false;
        }
    }

    /**
     * 模拟 滑块滑动
     * @param start_x 屏幕x轴 开始位置
     * @param start_y 屏幕y轴 开始位置
     * @param end_x 屏幕x轴 结束位置
     * @param end_y 屏幕y轴 结束位置
     * @param time 滑动耗时
     */
    public void swipeByAdb(String start_x, String start_y, String end_x, String end_y, String time){
        String adbCommand = "adb shell input swipe %s %s %s %s %s %s";
        adbCommand = String.format(adbCommand, start_x, start_y, end_x, end_y, time);
        System.out.println(adbCommand);
        AutoTools.runCMD(adbCommand);
    }

    public boolean swipeByElement(String locationExpression, int end_x, int end_y){
        MobileElement element = findElement(locationExpression);
        if (element == null) return false;

        TouchAction<?> touchAction = new TouchAction<>(androidDriver);
        try {
            touchAction
                    .longPress(LongPressOptions
                            .longPressOptions()
                            .withElement(ElementOption.element(element)))
                    .moveTo(PointOption.point(end_x, end_y))
                    .release()
                    .perform();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("滑动元素失败");
            return false;
        }

    }

    /**
     * 使用adb 命令输入 文本
     * @param text 输入的值
     */
    public void adbText(String text){
        AutoTools.runCMD("adb shell input text " + text);
    }

    public void adbClear(String LocationExpression){
        MobileElement element = findElement(LocationExpression);
        if(element != null){
            adbClear(element);
        }

    }
    public void adbClear(MobileElement element){
        String elementText = element.getText();
        adbClear(elementText.length());

    }
    private void adbClear(int length){
        adbKeyEvent(Integer.toString(AndroidKey.MOVE_END.getCode()));
        for(int i=0; i < length; i++){
            adbKeyEvent(Integer.toString(AndroidKey.DEL.getCode()));
        }
    }

    /**
     * adb 键盘事件
     * @param keyEvent 键盘事件
     */
    public void adbKeyEvent(String keyEvent){
        AutoTools.runCMD("adb shell input keyevent " + keyEvent);
    }

    public void assertFindElement(String locationExpression){

        MobileElement element = findElement(locationExpression);
        Assertions.assertNotNull(element);
    }

    public void sleep(int times){
        if (times <= 0){
            times = 1000;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(times);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }

    public MobileElement findElement(String locationExpression){

        if(androidDriver == null) createNewDriver();
        MobileElement mobileElement = null;
        // 当前活动页是加载界面 则睡两秒
        while (loadingActive.contains(androidDriver.currentActivity())){
            sleep(2000);
        }

        try {
            if(locationExpression.contains(":id/")) {
                mobileElement= (MobileElement) androidDriver.findElementById(locationExpression);
            }else if (locationExpression.contains("//")){
                mobileElement= (MobileElement) androidDriver.findElementByXPath(locationExpression);
            }else {
                mobileElement= (MobileElement) androidDriver.findElementsByAccessibilityId(locationExpression);
            }
        }catch (Exception e){
            System.out.println("未找到元素, 元素表达式为: " + locationExpression);
            // 当前测试app 奔溃 检查到的活动页为 .Launcher， 如果是其他的app 程序，请获取APP 奔溃后的 活动页，修修改
            if(crashActive.contains(androidDriver.currentActivity())){
                crashApp();
            }
            // NoSuchElementException
        }
        return mobileElement;
    }

    /**
     * 用于类似 toast 等浮动弹框元素的查找
     * @param value 需要定位元素的 文本值
     * @return boolean
     */
    public boolean findElementByWebDriverWait(String value){

        if(androidDriver == null) createNewDriver();
        try {
            new WebDriverWait(androidDriver, 5)
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath(String.format("//*[contains(@text,\"%s\")]", value))));
            return true;
        }catch (Exception e){
            System.out.println("定位浮动元素失败: " + value);
            System.out.println(e.getMessage());
            // NoSuchElementException
        }
        return false;
    }

    public void close(){
        if(androidDriver ==null){
            return;
        }
        try {
            androidDriver.quit();
        }finally {
            androidDriver = null;
        }
    }

    private void createNewDriver(){
        if (androidDriver != null) close();
        // AppDriver 本来设计为单列，测试中发现app容易奔溃导致appium 需要重新链接，这里有点打破单利模式的样子
        while (androidDriver == null) {
            androidDriver = AppDriver.getInstance().createNewDriver();
            sleep(1000);
        }
    }
    public AndroidDriver<?> getAndroidDriver() {
        return androidDriver;
    }

//    public AppUtil setAndroidDriver(AndroidDriver<?> androidDriver) {
//        this.androidDriver = androidDriver;
//        return this;
//    }

    /**
     * App 奔溃后的处理方法：关闭driver, 重新获取driver, 重新登陆app
     */
    public void crashApp(){
        close();
        createNewDriver();
        Login login = new Login();
        login.login(login.getPassWord());
    }
}
