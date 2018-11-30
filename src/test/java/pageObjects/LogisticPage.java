package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class LogisticPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/scSwitch")
    private WebElement logisticIsPossibleSwitch;

    @FindBy(id = "ru.agroclub:id/ctiLayout")
    private WebElement logisticRadiusField;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement acceptButton;

    public LogisticPage(WebActions webActions) {
        super(webActions);

    }

    public void assertion(){
        Assert.assertTrue("неверный заголовок страницы",currentPage());
        Assert.assertTrue("отсутствует переключатель возможности логистики",logisticIsPossibleSwitch!=null);
        Assert.assertTrue("отсутствует поле ввода радиуса логистики",logisticRadiusField!=null);
        Assert.assertTrue("отсутствует кнопка продолжения",acceptButton!=null);

    }

    public void clickOnlogisticIsPossibleSwitch() {
            web_a.waitToBeClickableAndClick(logisticIsPossibleSwitch);

    }


    public Boolean currentPage() {
        return web_a.isPresent(toolBarTitle).getText().equals("Логистика");

    }

    public void clickOnAcceptButton() {
        web_a.waitToBeClickableAndClick(acceptButton);

    }

    public void insertOnLogisticRadiusField(String logisticRadius) {
        web_a.waitToBeClickable(logisticRadiusField).sendKeys(logisticRadius);
    }
}


