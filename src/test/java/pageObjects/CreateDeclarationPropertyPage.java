package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.MainAgroPage;

import java.util.List;

public class CreateDeclarationPropertyPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement filtersWebList;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/btn")
    private WebElement confirmButton;

    private Swipe swipePropertesDown;
    private final Swipe swipedPropertisUp;


    public enum Sign{MORE, LESS}

    public CreateDeclarationPropertyPage(WebActions webActions) {
        super(webActions);
        swipePropertesDown = new Swipe(web_a, 50, 73, 50, 45, 600);
        swipedPropertisUp = new Swipe(web_a, 50, 45, 50, 73, 200);
    }

    public String getTitle() {
        return titleLabel.getText();
    }


    public void clickOnConfirmButton() {
        while (web_a.isPresent(confirmButton, 2) == null)
            web_a.swipeAction(swipePropertesDown);
        web_a.waitToBeClickableAndClick(confirmButton);
    }


    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public String setProperty(String property, String value, Sign sign) {
        if (property == null) {
            return null;
        } else {
            WebElement parentPropertyField = getPropertyElem(property);

            if (web_a.isPresent(parentPropertyField, By.id("ru.agroclub:id/spinner"), 1) != null) {
                web_a.waitToBeClickableAndClick(parentPropertyField);

                web_a.isPresent(null, By.xpath("//*[@text='" + value + "']"), 3).click();

                // parentPropertyField.findElement(By.xpath("//*[@text='" + value + "']")).click();

                return property;
            } else {
                web_a.insertTextOnAndroidEditTextField(parentPropertyField, value);

                switch (sign) {
                    case LESS: {
                        web_a.waitToBeClickableAndClick(parentPropertyField.findElement(By.id("ru.agroclub:id/tvLess")));
                        break;
                    }
                    case MORE: {
                        web_a.waitToBeClickableAndClick(parentPropertyField.findElement(By.id("ru.agroclub:id/tvMore")));
                        break;
                    }
                }
                return property;
            }


        }

    }

    public WebElement getPropertyElem(String fieldName) {

        List<WebElement> fieldsList;
        int min = -5;
        int swipeCount = 5;


        while (swipeCount > min) {

            WebElement present = web_a.isPresent(null, By.xpath("//*[contains(@text, '" + fieldName + "')]"), 2);

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

}


