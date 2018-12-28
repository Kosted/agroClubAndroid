package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HarvestBuyConditionPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement confirmButton;

    private final Swipe swipedPropertisUp;
    private final Swipe swipedPropertisDown;


    public HarvestBuyConditionPage(WebActions webActions) {
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


    public void switchPrepayment(boolean status) {
        WebElement webElement = getPropertyField("Осуществляете логистику").findElement(By.id("ru.agroclub:id/scSwitch"));
        if ((webElement.getText().equals("ОТКЛ.") && status == true) || (webElement.getText().equals("ВКЛ.") && status == false))
            webElement.click();
    }





}


