package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class LoginPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/etPhone")
    private WebElement phoneField;

    @FindBy(id = "ru.agroclub:id/rbFarmer")
    private WebElement farmerCheckBox;

    @FindBy(id = "ru.agroclub:id/rbPurchaser")
    private WebElement purchaserCheckBox;

    @FindBy(id = "ru.agroclub:id/btnLogin")
    private WebElement loginButton;

    @FindBy(id = "ru.agroclub:id/tvInfo")
    private WebElement personalAccept;

    @FindBy(id = "ru.agroclub:id/tvGuestLogin")
    private WebElement guestLoginButton;

    public LoginPage(WebActions webActions) {
        super(webActions);

    }


    public void insertPhoneNumber(String phoneNumber) {
        web_a.waitToBeClickable(phoneField).sendKeys(phoneNumber);

//        while (web_a.isPresent(phoneField).getText().length()<phoneNumber.length()+) {
//            web_a.waitToBeClickable(phoneField).clear();
//            web_a.waitToBeClickable(phoneField).sendKeys(phoneNumber);
//        }

    }

    public void chouseFarmer() {
        web_a.waitToBeClickableAndClick(farmerCheckBox);

    }

    public void chousePurchaser() {
        web_a.isPresent(purchaserCheckBox, 30).click();

    }

    public void clickOnLoginButton() {
        web_a.waitToBeClickableAndClick(loginButton);

    }

    public void clickOnPersonalAccept() {
        web_a.waitToBeClickableAndClick(personalAccept);

    }

    public void assertion(){
        Assert.assertTrue("отсутствует кнопка логина",loginButton!=null);
        Assert.assertTrue("отсутствует поле ввода телефона",phoneField!=null);
        Assert.assertTrue("отсутствует чекбокс фермера",farmerCheckBox!=null);
        Assert.assertTrue("отсутствует чекбокс закупщика",purchaserCheckBox!=null);
        Assert.assertTrue("отсутствует кнопка персонального соглашения",personalAccept!=null);
        Assert.assertTrue("отсутствует кнопка гостевого входа",guestLoginButton!=null);
    }

    public Boolean loginButtonIsEnabled(){
        return loginButton.isEnabled();
    }

    public WebElement getPhoneField() {
        return phoneField;
    }

    public WebElement getFarmerCheckBox() {
        return farmerCheckBox;
    }

    public WebElement getPurchaserCheckBox() {
        return purchaserCheckBox;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
}


