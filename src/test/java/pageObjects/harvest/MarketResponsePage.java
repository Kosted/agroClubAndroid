package pageObjects.harvest;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.MainAgroPage;

public class MarketResponsePage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;


    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement doOfferButton;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    public MarketResponsePage(WebActions webActions) {
        super(webActions);
    }


    //public Boolean currentPage() {
//        return web_a.isPresent(titleLabel).getText().equals("Рынок");
//    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void clickOnDoOfferButton() {
        swipePropertyDown(1);

        web_a.waitToBeClickableAndClick(doOfferButton);
    }

    public void setVolume(String volume){
        setField("Обьем", volume);
    }

    public void chouseDelivary(whoDelivers value) {
        WebElement webElement = getPropertyField("Цена, \u20BD/кг без НДС");
        switch (value) {
            case I:
                web_a.isPresent(webElement, By.id("ru.agroclub:id/clFirstItem"), 3).click();
                break;

            case COUNTERAGENT:
                web_a.isPresent(webElement, By.id("ru.agroclub:id/clSecondItem"), 3).click();
                break;
        }
    }
    public void chousePaymentCondition(paymentCondition value) {
        WebElement webElement = getPropertyField("Условия оплаты");
        switch (value) {
            case PREPAYMENT:
                web_a.isPresent(webElement, By.id("ru.agroclub:id/clFirstItem"), 3).click();
                break;

            case PAYMENTDELAY:
                web_a.isPresent(webElement, By.id("ru.agroclub:id/clSecondItem"), 3).click();
                break;
        }
    }

    public enum whoDelivers {I, COUNTERAGENT}

    public enum paymentCondition {PREPAYMENT, PAYMENTDELAY}

}


