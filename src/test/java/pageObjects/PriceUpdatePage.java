package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class PriceUpdatePage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    @FindBy(id = "ru.agroclub:id/btnConfirm")
    private WebElement confirmButton;

    public PriceUpdatePage(WebActions webActions) {
        super(webActions);

    }


    public void assertion(){
        Assert.assertTrue("неверный заголовок страницы",currentPage());

        Assert.assertTrue("отсутствует кнопка продолжения",confirmButton!=null);

    }

    public Boolean currentPage() {
        return  web_a.isPresent(toolBarTitle).getText().equals("Обновление цен");

    }

    public void clickOnConfirmButton() {
        web_a.waitToBeClickableAndClick(confirmButton);

    }

}


