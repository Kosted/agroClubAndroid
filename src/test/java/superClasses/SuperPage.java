package superClasses;

import helperClasses.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public abstract class SuperPage {

    protected WebDriver driver;
    protected WebActions web_a;

    public SuperPage(WebActions web_a) {
        this.web_a = web_a;
        driver = web_a.getDriver();

        PageFactory.initElements(driver, this);

    }



}
