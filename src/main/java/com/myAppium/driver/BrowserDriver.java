package com.myAppium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BrowserDriver {
    /**
     * 暂时只支持chrome
     *  chrome driver 下载地址：http://chromedriver.storage.googleapis.com/index.htm
     *  需要将chromeDriver 下载到chrome 的安装目录
     */

    private WebDriver webDriver;

    private BrowserDriver(){
        initDriver();
    }
    private static class WebDriverHolder{
        private final static BrowserDriver INSTANCE = new BrowserDriver();
    }

    public static BrowserDriver getInstance(){
        return WebDriverHolder.INSTANCE;
    }

    private void initDriver(){
        String OSName = System.getProperty("os.name").toLowerCase();
        String driverPath = "src\\main\\resources\\static\\wenDriver\\chromeDriver\\";
//        String driverPath = this.getClass().getResource("static\\wenDriver\\chromeDriver\\chromedriver_win32\\chromedriver.exe").toString();
        if(OSName.contains("windows")){
            // 设置 chrome 的路径 问题：为啥 chromedriver.exe 没有放在chrome 浏览器的根目录下，则报错呢？ 以后在找问题
            driverPath +="chromedriver_win32\\chromedriver.exe";
        }else if(OSName.contains("linux")) {
            driverPath += "chromedriver_linux64\\chromedriver.exe";
        }else{
            driverPath += "chromedriver_mac64\\chromedriver.exe";
        }


        System.out.println(driverPath);
//        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        //设置临时环境变量，指定chrome使用静默模式，减少日志输出量
        System.setProperty("webdriver.chrome.silentOutput", "true");
        /**
         * Chromeoption对象可以为chrome启动时定制许多参数，需要用到更多参数可以查阅chromeoptions相关说明。
         */
        // chrome浏览器参数对象
        ChromeOptions option = new ChromeOptions();
        //设置自动化启动时，不显示正在受到自动化软件控制的提示栏
        option.setExperimentalOption("excludeSwitches", new String[] {"enable-automation","load-extension"});
        /**
         * 加载chrome用户文件，这里使用的是浏览器默认存储的用户文件目录。 在chrome浏览器里通过地址栏里输入chrome://version
         * 进行访问，能够看到个人资料路径 注意个人资料路径中复制时，只需要到User Data这一级，不需要Default这一级
         * 使用时会和手动打开的浏览器冲突，注意不要同时打开。
         */
//		option.addArguments("--user-data-dir=C:\\Users\\pc\\AppData\\Local\\Google\\Chrome\\User Data");
        // 也可以将浏览器路径下的User Data目录复制一份放到其它位置进行引用，这样不会和手动打开的浏览器产生冲突。
//        option.addArguments("--user-data-dir=D:\\chromeData\\copyData");
        // 最大化浏览器窗口
        option.addArguments("--start-maximized");

        try {
            webDriver = new ChromeDriver(option);
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("chrome 启动成功");
        }catch (Exception e){
            System.out.println("chrome 启动失败: " + e.getMessage());
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
