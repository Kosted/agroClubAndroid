package helperClasses;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.chrome.ChromeDriver;

public class WebActions {

    protected WebDriverWait wait1;
    protected WebDriverWait wait2;
    protected WebDriverWait wait5;
    protected WebDriverWait wait10;
    protected WebDriverWait wait30;
    protected AndroidDriver androidDriver;
    protected AppiumDriver driver;

    protected int defaultImlicityWait;

    public WebActions(String driverMod) throws MalformedURLException {
        //super();
        defaultImlicityWait = 30;

        if (driverMod.equals("WEB"))
            createWebDriver();

        else if (driverMod.equals("ANDROID"))
            createAndroidDriver();

        else if (driverMod.equals("IOS"))
            createIOSDriver();

        wait1 = new WebDriverWait(driver, 1);
        wait2 = new WebDriverWait(driver, 2);
        wait5 = new WebDriverWait(driver, 5);
        wait10 = new WebDriverWait(driver, 10);
        wait30 = new WebDriverWait(driver, 30);

    }

    public void waitForAjax() {

        try {

            ExpectedCondition<Boolean> expectation;
            expectation = new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driverjs) {

                    JavascriptExecutor js = (JavascriptExecutor) driverjs;
                    return js.executeScript("return((window.jQuery != null) && (jQuery.active === 0))").equals("true");
                }
            };
            wait1.until(expectation);
        } catch (TimeoutException exTimeout) {

            // fail code
        } catch (WebDriverException exWebDriverException) {

            // fail code
        }
        //return this;
    }

    public void createWebDriver() {

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setBrowserName("chrome");
//        capabilities.setPlatform(Platform.LINUX);

        DesiredCapabilities dc = DesiredCapabilities.chrome();
        //driver = new ChromeDriver(dc);

//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);

        driver.manage().timeouts().implicitlyWait(defaultImlicityWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        //driver.manage().window().setSize(new Dimension(1400, 1080)); // max 1920 * 1080
        //driver.manage().window().maximize();
        ///////////////////////////удобства
//        driver.manage().window().setSize(new Dimension(1366, 768)); // max 1920 * 1080
//        driver.manage().window().setPosition(new Point(1930,366));

    }

    public void createAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.7.0");//1.9.0
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("deviceName", "CQ3000HB6L");
        String appVersion = "12" + "1";
        capabilities.setCapability("app", "/home/k.malev/Загрузки/agroclub-" +"2.0.2"+"(" + appVersion +")-api(v1.0.6)-debug.apk");
        //capabilities.setCapability("app", "/home/k.malev/Загрузки/agroclub-2.0.1(111)-api(v1.0.5)-debug.apk");

        capabilities.setCapability("appPackage", "ru.agroclub");
        capabilities.setCapability("appActivity", "ru.agroclub.app.splash.SplashView");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("newCommandTimeout", "300");
        capabilities.setCapability("autoGrantPermissions",  true);
        capabilities.setCapability("automationName", "uiautomator2");
        //capabilities.setCapability("automationName", "selendroid");
        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        } catch (Exception e) {
            System.out.println("не удалось создать драйвер " +  e.getMessage());
          //  e.printStackTrace();

        }
        driver.manage().timeouts().implicitlyWait(defaultImlicityWait, TimeUnit.SECONDS);

    }

    public void createIOSDriver() {
        //TO DO
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement waitToBeClickable(WebElement webElement) {
        return wait30.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitToBeClickable(WebElement webElement, int waitTime) {
        WebDriverWait finalWaitTime;
        switch (waitTime) {
            case 1: {
                finalWaitTime = wait1;
                break;
            }
            case 2: {
                finalWaitTime = wait2;
                break;
            }
            case 5: {
                finalWaitTime = wait5;
                break;
            }
            case 10: {
                finalWaitTime = wait10;
                break;
            }
            case 30: {
                finalWaitTime = wait30;
                break;
            }
            default: {
                finalWaitTime = new WebDriverWait(driver, waitTime);
                break;
            }
        }
        return finalWaitTime.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public Boolean waitToBeClickableAndClick(WebElement webElement) {
        try {
            waitToBeClickable(webElement);
        } catch (Exception e) {
            System.out.println("не был кликабелен и нажат элемент");
            return false;
        }
        System.out.println(webElement.getTagName() + " был кликабелен и нажат");
        webElement.click();
        return true;
    }

    public Boolean waitToBeClickableAndClick(WebElement webElement, int waitTime) {
        try {
            waitToBeClickable(webElement, waitTime);
        } catch (Exception e) {
            return false;
        }
        webElement.click();
        return true;
    }

    public void waitChangeUrl(String url) {
        wait2.until(ExpectedConditions.urlContains(url));
    }

    public Boolean waitStalenessOf(WebElement webElement) {
        try {

            wait10.until(ExpectedConditions.invisibilityOf(webElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public void waitInvisibilityOfElementLocated(WebElement webElement) {
//        wait10.until(ExpectedConditions.invisibilityOfElementLocated((By) webElement));
//    }


    public void clearField(WebElement field) {
        int charCount = field.getAttribute("value").length();
        for (int i = 0; i < charCount; i++) {
            field.sendKeys("\b");
        }
    }

    public WebElement isPresent(WebElement webElement, int waitTime) {
        try {
            WebDriverWait finalWaitTime;
            switch (waitTime) {
                case 1: {
                    finalWaitTime = wait1;
                    break;
                }
                case 2: {
                    finalWaitTime = wait2;
                    break;
                }
                case 5: {
                    finalWaitTime = wait5;
                    break;
                }
                case 10: {
                    finalWaitTime = wait10;
                    break;
                }
                case 30: {
                    finalWaitTime = wait30;
                    break;
                }
                default: {
                    finalWaitTime = new WebDriverWait(driver, waitTime);
                    break;
                }
            }
            finalWaitTime.until(ExpectedConditions.visibilityOf(webElement));

        } catch (Exception e) {
            System.out.println("элемент не найден");
            return null;
        }
        System.out.println("элемент найден " + webElement.getTagName());
        return webElement;
    }


    //переделать
    // придумать как искать элемент не от драйвера и в тоже время использовать средства селениума для проверки видимости visibilityOfelementLocated
    public WebElement isPresent(WebElement searchpoint, By locator, int waitTime) {

//вернуть к исходному виду:
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        try {
            if (searchpoint != null)
                searchpoint.findElement(locator);
            else
                driver.findElement(locator);
            driver.manage().timeouts().implicitlyWait(defaultImlicityWait, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.out.println("элемент не найден");
            driver.manage().timeouts().implicitlyWait(defaultImlicityWait, TimeUnit.SECONDS);
            return null;
        }
        driver.manage().timeouts().implicitlyWait(defaultImlicityWait, TimeUnit.SECONDS);
        System.out.println("элемент найден");
        if (searchpoint != null)
            return searchpoint.findElement(locator);
        else return driver.findElement(locator);

    }

    public WebElement isPresent(WebElement webElement) {
        try {
            wait10.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            System.out.println("элемент не найден");
            return null;
        }
        System.out.println("элемент найден " + webElement.getTagName());
        return webElement;
    }

    public void swipeAction(Swipe swipe) {

        TouchAction action = new TouchAction(this.driver);
        action.press(swipe.start)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipe.time)))
                .moveTo(swipe.end)
                .release()
                .perform();


    }

    public void skrollY(int num) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + 0 + ","
                + num + ");");
        waitForAjax();
    }

}
