package superClasses;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Thread.sleep;

public abstract class SuperPage {

    protected WebDriver driver;
    protected WebActions web_a;
    private final Swipe swipedPropertisUp;
    private final Swipe swipedPropertisDown;

    public SuperPage(WebActions web_a) {
        this.web_a = web_a;
        driver = web_a.getDriver();
        swipedPropertisDown = new Swipe(web_a, 50, 73, 50, 45, 600);
        swipedPropertisUp = new Swipe(web_a, 50, 45, 50, 73, 200);
        PageFactory.initElements(driver, this);

    }
    public String setField(String property, String value) {

        WebElement parentPropertyField = getPropertyField(property);

        if (/*parentPropertyField.getAttribute("className").equals("android.widget.EditText")*/value!=null)
            parentPropertyField.sendKeys(value);

        else {
            web_a.waitToBeClickableAndClick(parentPropertyField);

        }
        return property;


        //

//        if (web_a.isPresent(parentPropertyField, By.className("android.widget.EditText"), 1) != null) {
//            web_a.insertTextOnAndroidEditTextField(parentPropertyField, value);
//
//            //web_a.isPresent(null, By.xpath("//*[@text='" + value + "']"), 3).click();
//
//            // parentPropertyField.findElement(By.xpath("//*[@text='" + value + "']")).click();
//
//            return property;
//        } else {
//            web_a.waitToBeClickableAndClick(parentPropertyField);
//
////
//            return property;
//        }
    }

    public WebElement getPropertyField(String fieldName) {

        List<WebElement> fieldsList;
        int min = -5;
        int swipeCount = 5;


        while (swipeCount > min) {

            WebElement present = web_a.isPresent(null, By.xpath("//*[contains(@text, '" + fieldName + "')]"), 2);
            if (present.getAttribute("className").equals("android.widget.EditText"))
                return present;

            if (present !=null) {

                fieldsList = web_a.getDriver().findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));

                for (WebElement webElement : fieldsList) {

                    present = web_a.isPresent(webElement, By.xpath("//*[contains(@text, '" + fieldName + "')]"), 2);

                    if (present != null) {
                        return webElement;
                    }
                }
            }
            if (swipeCount != min)
                swipePropertyDown(1);
            else {
                min ++ ;
                swipeCount = min * -1;
                swipePropertyUp(4);

            }
            swipeCount-=2;
        }

        return null;
    }

    protected void swipePropertyDown(int times) {
        while (times > 0) {
            web_a.swipeAction(swipedPropertisDown);
            System.out.println("swipePropertyDown");
            times--;
        }
    }

    protected void swipePropertyUp(int times) {
        while (times > 0) {
            web_a.swipeAction(swipedPropertisUp);
            System.out.println("swipePropertyUp");
            times--;
        }
    }


}
