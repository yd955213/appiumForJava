package com.myAppium.app;

import com.myAppium.driver.BrowserDriver;
import com.myAppium.entity.browserType.MyBrowserType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.ObjectUtils;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebUtil {
    /**
     * 默认使用 chrome 浏览器
     * webDriver 获取driver 的方法写成了单例模式，测试中如果driver.quit()或者close()后，driver 会为空，请只在UI测试完成后在调用quit()方法
     */
    private WebDriver webDriver;
    private String browserType = MyBrowserType.CHROME;

    public WebUtil() {
        switch (browserType) {
            case MyBrowserType.FIRE_FOX:
                // 待开发
                break;
            case MyBrowserType.IE:
                // 待开发
                break;
            default:
                // 默认使用 chrome 浏览器
                webDriver = BrowserDriver.getInstance().getWebDriver();
                break;
        }
    }

    public WebUtil setBrowserType(String browserType) {
        this.browserType = browserType;
        return this;
    }

    public void getWindowHandles() {
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String s : windowHandles) {
            System.out.println("windowHandles = " + s);
        }
    }

//    public void switchTo(){
//        webDriver.switchTo().
//    }

    public void openBrowser(String url) {
        try {
            webDriver.get(url);
        } catch (Exception e) {
            System.out.println("打开网址失败： " + e.getMessage());
        }
    }

    //显式等待，等待标题变为指定内容
    public void waitTitle(String expectTitle) {
        try {
            //设置一个上限为10秒的显式等待。
            WebDriverWait await = new WebDriverWait(webDriver, 10);
            //设置等待条件是标题包含指定内容
            await.until(ExpectedConditions.titleContains(expectTitle));
        } catch (Exception e) {
            System.out.println("显式等待标题变为" + expectTitle + "失败");
            e.printStackTrace();
        }
    }

    /**
     * 暂时支持 xpath, id, class , css选择器
     * 规定
     *
     * @param locationExpression
     * @return
     */
    public WebElement findElement(String locationExpression) {
        WebElement webElement = null;
        if (locationExpression.startsWith("\\.")) {
            webElement = findElementByClassName(locationExpression.substring(1));
        } else if (locationExpression.startsWith("#")) {
            webElement = findElementById(locationExpression.substring(1));
        } else if (locationExpression.startsWith("//")) {
            webElement = findElementXpath(locationExpression);
        } else {
            webElement = findElementByCssSelector(locationExpression);
        }
        return webElement;
    }

    public List<WebElement> findElements(String locationExpression) {
        List<WebElement> elements = null;
        String errMsg = "查找元素失败，表达式为：%s, 异常信息为：%s";
        if (locationExpression.startsWith("\\.")) {
            try {
                elements = webDriver.findElements(By.className(locationExpression.substring(1)));
            } catch (Exception e) {
                System.out.println(String.format(errMsg, locationExpression, errMsg));
            }
        } else if (locationExpression.startsWith("#")) {
            try {
                elements = webDriver.findElements(By.id(locationExpression.substring(1)));
            } catch (Exception e) {
                System.out.println(String.format(errMsg, locationExpression, errMsg));
            }
        } else if (locationExpression.startsWith("//")) {
            try {
                elements = webDriver.findElements(By.xpath(locationExpression));
            } catch (Exception e) {
                System.out.println(String.format(errMsg, locationExpression, errMsg));
            }
        } else {
            try {
                elements = webDriver.findElements(By.cssSelector(locationExpression));
            } catch (Exception e) {
                System.out.println(String.format(errMsg, locationExpression, errMsg));
            }
        }
        return elements;
    }

    public WebElement findElementByCssSelector(String locationExpression) {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.cssSelector(locationExpression));
        } catch (Exception e) {
            System.out.println(String.format("查找元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public WebElement findElementById(String locationExpression) {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.id(locationExpression));
        } catch (Exception e) {
            System.out.println(String.format("查找元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public WebElement findElementByClassName(String locationExpression) {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.className(locationExpression));
        } catch (Exception e) {
            System.out.println(String.format("查找元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public WebElement findElementXpath(String locationExpression) {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.xpath(locationExpression));
        } catch (Exception e) {
            System.out.println(String.format("查找元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public boolean input(String locationExpression, String value) {
        WebElement element = null;
        try {
            element = findElement(locationExpression);
            element.clear();
            element.sendKeys(value);
            return true;
        } catch (Exception e) {
            System.out.println(String.format("文本框输入值失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return false;
    }

    public WebElement inputByXPath(String locationExpression, String value) {
        WebElement element = null;
        try {
            element = findElementXpath(locationExpression);
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println(String.format("文本框输入值失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public boolean click(String locationExpression) {
        WebElement element = null;
        try {
            element = findElement(locationExpression);
            element.click();
            return true;
        } catch (Exception e) {
            System.out.println(String.format("点击元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return false;
    }

    public WebElement clickByXPath(String locationExpression) {
        WebElement element = null;
        try {
            element = findElementXpath(locationExpression);
            element.click();
        } catch (Exception e) {
            System.out.println(String.format("点击元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return element;
    }

    public String getText(String locationExpression) {
        WebElement element = findElement(locationExpression);
        if (element == null) return null;

        String value = element.getText();

        if (ObjectUtils.isEmpty(value)) return element.getAttribute("value");

        return value;
    }

    public void runJs(String JsString) {
        try {
            JavascriptExecutor JsExecutor = (JavascriptExecutor) this.webDriver;
            Object o = JsExecutor.executeScript(JsString);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("执行js语句失败，表达式为：%s, 异常信息为：%s", JsString, e.getMessage()));
//            return null;
        }
    }

    public String getTextByXPath(String locationExpression) {
        String value = null;
        WebElement element;
        try {
            element = findElementXpath(locationExpression);
            value = element.getText();
        } catch (Exception e) {
            System.out.println(String.format("点击元素失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
        return value;
    }

    /**
     * 强制等待时间， 自动化测试由于电脑操作过快，但页面刷新不及时会导致未找到元素或者获取的文本值为空，写自动化脚本时需要强制等待下
     *
     * @param times
     */
    public void sleep(int times) {
        try {
            TimeUnit.MILLISECONDS.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭driver,请在整个UI测试完成后在调用，测试中调用会导致 webDriver 一直为null 直到测试结束；
     */
    public void quit() {
        if (webDriver != null) {
            try {
                webDriver.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     * 进行鼠标悬停操作
     *
     * @param locationExpression
     */
    public void hover(String locationExpression) {
        try {
            Actions actions = new Actions(webDriver);
            WebElement element = findElement(locationExpression);
            //移动到某个元素，并且悬停
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println(String.format("鼠标悬停失败，表达式为：%s, 异常信息为：%s", locationExpression, e.getMessage()));
        }
    }
}
