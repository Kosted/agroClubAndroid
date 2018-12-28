package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateNewDeclarationPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement confirmButton;

    private final Swipe swipedPropertisUp;
    private final Swipe swipedPropertisDown;


    public CreateNewDeclarationPage(WebActions webActions) {
        super(webActions);
        swipedPropertisDown = new Swipe(web_a, 50, 73, 50, 45, 600);
        swipedPropertisUp = new Swipe(web_a, 50, 45, 50, 73, 200);
    }

    public String getTitle() {
        return titleLabel.getText();
    }


    public void clickOnConfirmButton() {
        while (web_a.isPresent(confirmButton, 2) == null)
            web_a.swipeAction(swipedPropertisDown);
        web_a.waitToBeClickableAndClick(confirmButton);

    }

    public void farmerChousDeclarationType(MarcetplacePage.MarketSections marketSections) {
        String locator = "ru.agroclub:id/cv";
        switch (marketSections) {
            case HARVEST:
                locator += "SellHarvest";
                break;
//            case SEEDS:
//                locator+="Seeds";
//                break;
//            case SZR:
//                locator+="SZR";
//                break;
            case FERTILIZER:
                locator += "BuyFertilizer";
                break;
//            case COUNTERFEIT:
//                locator += "Counterfeit";
//                break;
        }
        web_a.isPresent(null, By.id(locator), 5).click();
    }


    public String setField(String property, String value) {

        WebElement parentPropertyField = getPropertyField(property);

        if (web_a.isPresent(parentPropertyField, By.className("android.widget.EditText"), 1) != null) {
            web_a.insertTextOnAndroidEditTextField(parentPropertyField, value);

            //web_a.isPresent(null, By.xpath("//*[@text='" + value + "']"), 3).click();

            // parentPropertyField.findElement(By.xpath("//*[@text='" + value + "']")).click();

            return property;
        } else {
            web_a.waitToBeClickableAndClick(parentPropertyField);


            return property;
        }


    }

    public void switchLogistic(boolean status) {
        WebElement webElement = getPropertyField("Осуществляете логистику").findElement(By.id("ru.agroclub:id/scSwitch"));
        if ((webElement.getText().equals("ОТКЛ.") && status == true) || (webElement.getText().equals("ВКЛ.") && status == false))
            webElement.click();
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
                else
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
            web_a.swipeAction(swipedPropertisDown);
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


