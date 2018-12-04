package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class RegistrationPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/tilFullName")
    private WebElement fullNameField;

    @FindBy(id = "ru.agroclub:id/tilEmail")
    private WebElement emailField;

    @FindBy(id = "ru.agroclub:id/btnSave")
    private WebElement saveButton;

    @FindBy(id = "ru.agroclub:id/toolbarRightBtn")
    private WebElement closeButton;

    public RegistrationPage(WebActions webActions) {
        super(webActions);

    }
    public void assertion(){
        Assert.assertTrue("отсутствует поле ввода ФИО",fullNameField!=null);
        Assert.assertTrue("отсутствует поле ввода имейла",emailField!=null);
        Assert.assertTrue("отсутствует кнопка сохранения",saveButton!=null);
        Assert.assertTrue("отсутствует кнопка прерывания регистрации",closeButton!=null);
    }

    public void insertFullName(String fullName) {

        web_a.waitToBeClickable(fullNameField).clear();

        web_a.insertTextOnAndroidEditTextField(fullNameField,fullName);
    }

    public void insertEmail(String email) {

        web_a.waitToBeClickable(emailField).clear();
        web_a.insertTextOnAndroidEditTextField(emailField,email);
    }

    public void clickOnSaveButton() {
        web_a.waitToBeClickableAndClick(saveButton);

    }

    public void clickOnCloseButton() {
        web_a.waitToBeClickable(closeButton).click();

    }

    public Boolean saveButtonIsEnabled(){
        return saveButton.isEnabled();
    }

    public WebElement getFullNameField() {
        return fullNameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }
}


