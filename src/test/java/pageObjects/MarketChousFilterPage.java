package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MarketChousFilterPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement filtersWebList;

    @FindBy(id = "ru.agroclub:id/toolbarLeftBtn")
    private WebElement defaultFiltersButton;

    @FindBy(id = "ru.agroclub:id/toolbarRightBtn")
    private WebElement closeFiltersButton;

    @FindBy(id = "ru.agroclub:id/btn")
    private WebElement confirmButton;

    private Swipe swipedFiltersDown;

    public MarketChousFilterPage(WebActions webActions) {
        super(webActions);
        swipedFiltersDown = new Swipe(web_a, 50, 73, 50, 45, 600);
    }

    public String getTitle() {
        return titleLabel.getText();
    }


    public void clickOnConfirmButton() {
        while (web_a.isPresent(confirmButton, 2) == null)
            web_a.swipeAction(swipedFiltersDown);
        web_a.waitToBeClickableAndClick(confirmButton);

    }


    public void clickOnCloseFiltersButton() {
        web_a.waitToBeClickableAndClick(closeFiltersButton);
    }

    public void clickOnDefaultFilterButton() {
        web_a.waitToBeClickableAndClick(defaultFiltersButton);
    }

    public String setFilter(String property, String value, String sign) {
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
                    case "less": {
                        web_a.waitToBeClickableAndClick(parentPropertyField.findElement(By.id("ru.agroclub:id/tvLess")));
                        break;
                    }
                    case "more": {
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

        while (!currentFieldsName.contains(fieldName)) {

            fieldsList = filtersWebList.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));

            for (WebElement webElement : fieldsList) {
                if (web_a.isPresent(webElement, By.className("android.widget.EditText"), 1) != null)
                    currentFieldsName = web_a.isPresent(webElement, By.className("android.widget.EditText"), 1).getText();
                else
                    currentFieldsName = webElement.findElement(By.className("android.widget.TextView")).getText();

                if (currentFieldsName.contains(fieldName)) {
                    return webElement;

                }
            }
            if (swipeCount > 0)
                swipeProperty();
            else return null;
            swipeCount--;
        }

        return null;
    }

    private void swipeProperty() {
        web_a.swipeAction(swipedFiltersDown);
    }


}


