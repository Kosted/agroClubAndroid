package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChouseOrganizationPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/tilOrganization")
    private WebElement organizationField;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement organizationWebList;

    @FindBy(id = "ru.agroclub:id/btnSave")
    private WebElement saveButton;

    @FindBy(id = "ru.agroclub:id/tvInfo")
    private WebElement infoLabel;

    @FindBy(id = "ru.agroclub:id/tilBranch")
    private WebElement subdivisionField;

    @FindBy(id = "ru.agroclub:id/toolbarRightBtn")
    private WebElement closeButton;

    @FindBy(id = "ru.agroclub:id/scSwitchVAT")
    private WebElement workWithNDSSwitcher;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    public ChouseOrganizationPage(WebActions webActions) {
        super(webActions);

    }

    public void clickOnBackButton(){
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void assertionOrganization() {
        Assert.assertTrue("отсутствует поле ввода организации", organizationField != null);
        Assert.assertTrue("отсутствует заголовок окна", toolBarTitle != null);
        Assert.assertTrue("неверный заголовок страницы", currentPage(30));
        Assert.assertTrue("отсутствует кнопка прерывания регистрации", closeButton != null);
    }

    public void assertionAcceptOrg() {
        Assert.assertTrue("неверный заголовок страницы", currentPage(30));
        Assert.assertTrue("отсутствует кнопка прерывания регистрации", closeButton != null);
        Assert.assertTrue("отсутствует кнопка сохранения", saveButton != null);
    }

    public void insertOrganization(String organization) {
        web_a.waitToBeClickable(organizationField).sendKeys(organization);

    }

    public void insertSubdivision(String subdivision) {
        web_a.isPresent(subdivisionField,3);
        while(web_a.isPresent(subdivisionField,1)==null) {
            clickOnBackButton();
            chouseOrganization(0);
            clickOnSaveButton();
        }
        web_a.waitToBeClickable(subdivisionField).sendKeys(subdivision);

    }

    public Boolean currentPage(int waitTime) {
        if (web_a.isPresent(toolBarTitle, waitTime) != null)
            return toolBarTitle.getText().equals("Укажите организацию");
        else return false;
    }

    public void chouseOrganization(int position) {
        web_a.isPresent(organizationWebList, 30);
        List<WebElement> orgList = organizationWebList.findElements(By.className("android.view.ViewGroup"));
        while (orgList.size() == 0) {
            orgList = organizationWebList.findElements(By.className("android.view.ViewGroup"));
        }
        web_a.waitToBeClickableAndClick(organizationWebList.findElements(By.className("android.view.ViewGroup")).get(position));

    }

    public void clickOnSaveButton() {
        web_a.waitToBeClickableAndClick(saveButton);

    }

    public void clickOnWorkWithNDSSwitcher() {
        web_a.waitToBeClickableAndClick(workWithNDSSwitcher);
    }

    public WebElement getOrganizationField() {
        return organizationField;
    }

    public WebElement getToolBarTitle() {
        return toolBarTitle;
    }

    public WebElement getOrganizationWebList() {
        return organizationWebList;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getInfoLabel() {
        return infoLabel;
    }

    public WebElement getSubdivisionField() {
        return subdivisionField;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }
}


