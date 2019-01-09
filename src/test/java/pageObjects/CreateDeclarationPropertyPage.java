package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateDeclarationPropertyPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement filtersWebList;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/btnBottom")
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
            WebElement parentPropertyField = getPropertyField(property);

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

    private WebElement getPropertyField(String fieldName) {

        List<WebElement> fieldsList;
        String currentFieldsName = "";
        int swipeCount = 5;

        while (!currentFieldsName.contains(fieldName) || swipeCount > 0) {

            fieldsList = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));

            for (WebElement webElement : fieldsList) {
                if (web_a.isPresent(webElement, By.className("android.widget.EditText"), 1) != null)
                    currentFieldsName = web_a.isPresent(webElement, By.className("android.widget.EditText"), 1).getText();

                else if (web_a.isPresent(webElement, By.className("android.widget.TextView"), 2) != null)
                    currentFieldsName = webElement.findElement(By.className("android.widget.TextView")).getText();

                if (currentFieldsName.contains(fieldName)) {
                    return webElement;

                }
            }
            if (swipeCount > 0)
                swipePropertyDown(1);
            else {
                swipeCount = 6;
                swipePropertyUp(4);

            }
            swipeCount--;
        }

        return null;
    }

    private void swipePropertyDown(int times) {
        while (times > 0) {
            web_a.swipeAction(swipePropertesDown);
            System.out.println("swipePropertyDown");
            times--;
        }
    }
    private void swipePropertyUp(int times) {
        while (times > 0) {
            web_a.swipeAction(swipedPropertisUp);
            System.out.println("swipePropertyUp");
            times--;
        }
    }

}


