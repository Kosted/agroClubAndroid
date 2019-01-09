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

    @FindBy(id = "ru.agroclub:id/btnSave")
    private WebElement confirmButton;

    @FindBy(id = "ru.agroclub:id/btnSave")
    private WebElement prepaymentSwitchElem;

    @FindBy(id = "ru.agroclub:id/IITwo")
    private WebElement paymentDelayButton;

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
//        while (web_a.isPresent(confirmButton, 2) == null)
//            web_a.swipeAction(swipedPropertisDown);
        web_a.waitToBeClickableAndClick(confirmButton);

    }

    public void clickOnPaymentDelayButton() {

        web_a.waitToBeClickableAndClick(paymentDelayButton);

    }

    public void chousePaymentDelay(PaymentDelay paymentDelay) {
        List<WebElement> options = driver.findElements(By.id("android:id/text1"));
        switch (paymentDelay) {
            case WITOUTDELAY:
                options.get(0).click();
                break;
            case FIVEDAYS:
                options.get(1).click();
                break;
            case THIRTYDAYS:
                options.get(2).click();
                break;
        }
    }


    public void switchPrepayment(boolean status) {
        if ((prepaymentSwitchElem.getText().equals("ОТКЛ.") && status == true) || (prepaymentSwitchElem.getText().equals("ВКЛ.") && status == false))
            prepaymentSwitchElem.click();
    }

    public enum PaymentDelay {WITOUTDELAY, FIVEDAYS, THIRTYDAYS}

}


