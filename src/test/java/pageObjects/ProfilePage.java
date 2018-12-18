package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

import java.util.List;

public class ProfilePage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/tilFullName")
    private WebElement fullNameField;
    @FindBy(id = "ru.agroclub:id/tilEmail")
    private WebElement emailField;
    @FindBy(id = "ru.agroclub:id/tilPhone")
    private WebElement numberField;
    @FindBy(id = "ru.agroclub:id/tvOrganizationInfo")
    private WebElement organizationInfoButton;

    @FindBy(id = "ru.agroclub:id/btnSave")
    private WebElement saveButton;

    public ProfilePage(WebActions webActions) {
        super(webActions);
    }


    public Boolean currentPage(int waitTime) {
        if (web_a.isPresent(titleLabel, waitTime) != null)
            return titleLabel.getText().equals("Данные профиля");
        else return false;
    }

    public Boolean saveButtonIsEnabled() {
        return saveButton.isEnabled();
    }


    public void clickOnSaveButton() {
        web_a.waitToBeClickableAndClick(saveButton);

    }

    public void insertFullName(String fullName) {
        web_a.waitToBeClickable(fullNameField).clear();
        web_a.insertTextOnAndroidEditTextField(fullNameField,fullName);
    }

    public String getFullName() {
        return web_a.waitToBeClickable(fullNameField).findElement(By.className("android.widget.EditText")).getText();
    }

    public String getEmail() {
        return web_a.waitToBeClickable(emailField).findElement(By.className("android.widget.EditText")).getText();
    }

    public String getNumber() {
        return web_a.waitToBeClickable(numberField).findElement(By.className("android.widget.EditText")).getText();
    }

    public String getOrganizationInfo() {
        return web_a.waitToBeClickable(organizationInfoButton).getText();
    }

    public void insertEmail(String email) {
        web_a.waitToBeClickable(emailField).clear();
        web_a.insertTextOnAndroidEditTextField(emailField,email);
    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void clickOnOrganizationButton() {
        web_a.waitToBeClickableAndClick(organizationInfoButton);
    }

    public void assertion() {
        Assert.assertTrue("отсутствует поле ввода ФИО",fullNameField!=null);
        Assert.assertTrue("отсутствует поле ввода имейла",emailField!=null);
        Assert.assertTrue("отсутствует поле содержащее мобильный телефон",numberField!=null);
        Assert.assertTrue("отсутствует поле содержащее название компании",organizationInfoButton!=null);
        Assert.assertTrue("отсутствует кнопка сохранения",saveButton!=null);
        Assert.assertTrue("отсутствует кнопка назад ",backButton!=null);
    }
}


