package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

import static java.lang.Thread.sleep;

public class SmsCodeAcceptPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/viewCode")
    private WebElement smsCodeField;

    @FindBy(id = "ru.agroclub:id/tvCode")
    private WebElement cheatCodeLabel;

    @FindBy(id = "ru.agroclub:id/tvSendCode")
    private WebElement resendSmsCodeButton;


    public SmsCodeAcceptPage(WebActions webActions) {
        super(webActions);

    }

    public void assertion() {
        Assert.assertTrue("отсутствует поле ввода смс кода", smsCodeField != null);
        Assert.assertTrue("отсутствует подсказка по смс коду ", cheatCodeLabel != null);
        Assert.assertTrue("отсутствует элемент отвечающий за повторную отравку смс кодов ", resendSmsCodeButton != null);
    }

    public void insertSmsCode(String smsCode) {
        web_a.waitToBeClickable(smsCodeField).clear();
        web_a.waitToBeClickable(smsCodeField).sendKeys(smsCode);

    }

    public String getSmsCode() {
        return web_a.isPresent(cheatCodeLabel, 10).getText();

    }

    public void insertCorrectSmsCode() {
        web_a.waitToBeClickable(smsCodeField).sendKeys(getSmsCode());

    }

    public Boolean resendCodeIsPossible() {
        if (resendSmsCodeButton != null)
            return resendSmsCodeButton.getText().equals("Отправить код");
        else
            return false;
    }

    public void clickOnResendCodeButton() {
        if (resendCodeIsPossible())
            web_a.waitToBeClickable(resendSmsCodeButton).click();
        else System.out.println(" нельзя отправить код заного");

    }

    public void absolutResendCode() {
        while (!resendCodeIsPossible()) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(" не вышло спать в абсолютном ресенде смс кода");
            }
        }
        clickOnResendCodeButton();
    }

    public WebElement getSmsCodeField() {
        return smsCodeField;
    }

    public WebElement getResendSmsCodeButton() {
        return resendSmsCodeButton;
    }

}


