package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.MainAgroPage;

import java.util.List;

public class CreateNewDeclarationPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/btn")
    private WebElement confirmButton;

//    private final Swipe swipedPropertisUp;
//    private final Swipe swipedPropertisDown;


    public CreateNewDeclarationPage(WebActions webActions) {
        super(webActions);
//        swipedPropertisDown = new Swipe(web_a, 50, 73, 50, 45, 600);
//        swipedPropertisUp = new Swipe(web_a, 50, 45, 50, 73, 200);
    }

    public String getTitle() {
        return titleLabel.getText();
    }


    public void clickOnConfirmButton() {
        while (web_a.isPresent(confirmButton, 2) == null)
            swipePropertyDown(1);
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

    public void switchLogistic(boolean status) {
        WebElement webElement = getPropertyField("Осуществляете логистику").findElement(By.id("ru.agroclub:id/scSwitch"));
        if ((webElement.getText().equals("ОТКЛ.") && status == true) || (webElement.getText().equals("ВКЛ.") && status == false))
            webElement.click();
    }

    public String setDeclarationField(String property, String value) {

        return setField(property,value);

    }

    public WebElement getDeclarationField(String fieldName) {
        return getPropertyField(fieldName);
    }


}


