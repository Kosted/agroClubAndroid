package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

import java.util.List;

public class ChousListPage extends SuperPage {

    private final Swipe swipedPropertisDown;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    public ChousListPage(WebActions webActions) {
        super(webActions);
        swipedPropertisDown = new Swipe(web_a, 50, 80, 50, 25, 600);
    }

//    public void assertion() {
//        Assert.assertTrue("неверный заголовок страницы", currentPage());
//        Assert.assertTrue("отсутствует переключатель возможности логистики", logisticIsPossibleSwitch != null);
//        Assert.assertTrue("отсутствует поле ввода радиуса логистики", logisticRadiusField != null);
//        Assert.assertTrue("отсутствует кнопка продолжения", acceptButton != null);
//
//    }

    public void clickOnPropertyField(String fieldName) {

        List<WebElement> fieldsList;
        String currentFieldsName = "";
        int swipeCount = 8;

        while (!currentFieldsName.contains(fieldName) && swipeCount > 0) {

            fieldsList = driver.findElements(By.id("ru.agroclub:id/tvName"));

            for (WebElement webElement : fieldsList) {

                    currentFieldsName = webElement.getText();

                if (currentFieldsName.contains(fieldName)) {
                    webElement.click();
                    return;

                }
            }
            if (swipeCount > 0)
                swipeProperty();
            else return;
            swipeCount--;
        }

        return;
    }

    private void swipeProperty() {
        web_a.swipeAction(swipedPropertisDown);
        System.out.println("swipeProperty");
    }

//    public Boolean currentPage() {
//        return web_a.isPresent(toolBarTitle).getText().equals("Логистика");
//
//    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);

    }

}


