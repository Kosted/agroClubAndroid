package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class AddAddressPage extends SuperPage {

    @FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private WebElement allowPermissionButton;

    @FindBy(id = "com.android.packageinstaller:id/permission_deny_button")
    private WebElement denyPermissionButton;

    @FindBy(id = "ru.agroclub:id/tilAddress")
    private WebElement addressNameField;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    @FindBy(id = "ru.agroclub:id/btnNext")
    private WebElement nextButton;

    @FindBy(id = "ru.agroclub:id/ivEdit")
    private WebElement manualEditAddresButton;

    public AddAddressPage(WebActions webActions) {
        super(webActions);


    }

    public void assertion(String role) {
        Assert.assertTrue("отсутствует заголовок страницы \"Укажите адрес приемки\"", currentPage(30, role));
        Assert.assertTrue("отсутствует поле ввода названия адресса", addressNameField != null);
        Assert.assertTrue("отсутствует кнопка далее", nextButton != null);
    }

    public void clickOnAllowPermissionButton() {
        if (web_a.isPresent(allowPermissionButton, 2) != null)
            web_a.waitToBeClickable(allowPermissionButton).click();

    }

    public void clickOnDenyPermissionButton() {
        if (web_a.isPresent(denyPermissionButton, 2) != null)
            web_a.waitToBeClickable(denyPermissionButton).click();

    }


    public Boolean currentPage(int waitTime, String role) {
        if (web_a.isPresent(toolBarTitle, waitTime) != null)
            if (role.equals("Закупщик"))
            return toolBarTitle.getText().equals("Укажите адрес приемки");
            else
                return toolBarTitle.getText().equals("Укажите адрес отгрузки");
        else return false;
    }

    public void clickOnNextButton() {
        web_a.waitToBeClickableAndClick(nextButton);

    }

    public void clickOnManualEditAddresButton() {
        web_a.waitToBeClickableAndClick(manualEditAddresButton);
    }

    public void insertOnAddressNameField(String address) {
        web_a.insertTextOnAndroidEditTextField(addressNameField,address);
    }



}


