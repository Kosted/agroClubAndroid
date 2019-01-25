package pageObjects.harvest;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMyDeclaration;
import superClasses.SuperPage;

import java.util.List;

public class MeetingPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;


    public MeetingPage(WebActions webActions) {
        super(webActions);
    }


    public Boolean currentPage() {
        return web_a.isPresent(toolBarTitle).getText().contains("Переговоры");
    }


    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);

    }

    @FindBy(id = "ru.agroclub:id/btnAccept")
    private WebElement acceptButton;

    public void clickOnAcceptButton() {
        web_a.waitToBeClickableAndClick(acceptButton);

    }


    @FindBy(id = "ru.agroclub:id/btnCounterOffer")
    private WebElement counterOfferButton;

    public void clickOnCounterOfferButton() {
        web_a.waitToBeClickableAndClick(counterOfferButton);

    }

    @FindBy(id = "ru.agroclub:id/btnReject")
    private WebElement rejectButton;

    public void clickOnRejectButton() {
        while (web_a.isPresent(rejectButton, 2) == null)
            swipePropertyDown(1);
        web_a.waitToBeClickableAndClick(rejectButton);

    }

    @FindBy(id = "ru.agroclub:id/btnDialogReject")
    private WebElement rejectOffersButton;

    public void clickOnRejectOffersButton() {
        web_a.waitToBeClickableAndClick(rejectOffersButton);

    }

    @FindBy(id = "ru.agroclub:id/btnCancel")
    private WebElement cancelButton;

    public void clickOnCancelButton() {
        web_a.waitToBeClickableAndClick(cancelButton);

    }

    @FindBy(id = "ru.agroclub:id/editTextPrice")
    private WebElement counterPriceField;

    public void insertCounterPrice(String text) {
        web_a.waitToBeClickable(counterPriceField).sendKeys(text);

    }

    @FindBy(id = "ru.agroclub:id/editTextVolume")
    private WebElement counterVolumeField;

    public void insertCounterVolume(String text) {
        web_a.waitToBeClickable(counterVolumeField).sendKeys(text);
    }

    @FindBy(id = "ru.agroclub:id/btnPublish")
    private WebElement publishCounterOffersButton;

    public void clickOnPublishCounterOffersButton() {
        web_a.waitToBeClickableAndClick(cancelButton);
    }



    public void doCounterOffers(String price, String volume){

            clickOnRejectButton();

            clickOnCounterOfferButton();

            insertCounterPrice(price);

            insertCounterVolume(volume);

            clickOnPublishCounterOffersButton();
    }

}


