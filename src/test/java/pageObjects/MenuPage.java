package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarRightBtn")
    private WebElement logOutButton;
    @FindBy(id = "ru.agroclub:id/clProfile")
    private WebElement profile;
    @FindBy(id = "ru.agroclub:id/clMyAddresses")
    private WebElement address;
    @FindBy(id = "ru.agroclub:id/clLogistic")
    private WebElement logistic;
    @FindBy(id = "ru.agroclub:id/clPriceRefreshing")
    private WebElement priceUpdate;
    @FindBy(id = "ru.agroclub:id/clBalance")
    private WebElement balance;
    @FindBy(id = "ru.agroclub:id/clDeals")
    private WebElement deals;
    @FindBy(id = "ru.agroclub:id/clNews")
    private WebElement news;
    @FindBy(id = "ru.agroclub:id/clAboutAgroclub")
    private WebElement aboutAgroclub;
    @FindBy(id = "ru.agroclub:id/clContactUs")
    private WebElement contactUs;
    @FindBy(id = "ru.agroclub:id/clAboutApp")
    private WebElement aboutApp;
    @FindBy(id = "ru.agroclub:id/tvUserInfo")
    private WebElement userInfoLabel;

    Swipe swipeDown;
    Swipe swipeUp;

    public MenuPage(WebActions webActions) {
        super(webActions);
        swipeDown = new Swipe(web_a, 50, 75, 50, 10, 200);
        swipeUp = new Swipe(web_a, 50, 10, 50, 75, 200);

    }


    //    public Boolean currentPage() {
//        return titleLabel.getText().equals("Рынок");
//    }
    public void clickOnLogautButton() {
        web_a.waitToBeClickableAndClick(logOutButton);
    }

    public void clickOnProfileButton() {
        web_a.waitToBeClickableAndClick(profile);
    }

    public void clickOnAddressButton() {
        web_a.waitToBeClickableAndClick(address);
    }

    public void clickOnLogisticButton() {
        web_a.waitToBeClickableAndClick(logistic);
    }

    public void clickOnPriceUpdateButton() {
        web_a.waitToBeClickableAndClick(priceUpdate);
    }

    public void clickOnBalanceButton() {
        web_a.waitToBeClickableAndClick(balance);
    }

    public void clickOnDealsButton() {
        web_a.waitToBeClickableAndClick(deals);
    }

    public void clickOnNewsButton() {
        web_a.swipeAction(swipeDown);
        web_a.swipeAction(swipeDown);
        web_a.waitToBeClickableAndClick(news);
    }

    public String getUserInfo() {

        if (web_a.isPresent(userInfoLabel)!=null)
            return userInfoLabel.getText();
        return null;
    }


}


