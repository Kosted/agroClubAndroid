package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class AddresManualAddPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/tilAddress")
    private WebElement addresField;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/btnNext")
    private WebElement nextButton;

    @FindBy(id = "ru.agroclub:id/tvGuestLogin")
    private WebElement guestLoginButton;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLable;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement addresWebList;

    @FindBy(id = "ru.agroclub:id/tvAddress")
    private WebElement currentAddresLabel;

    @FindBy(id = "ru.agroclub:id/tilHouseNumber")
    private WebElement houseNumberField;

    public AddresManualAddPage(WebActions webActions) {
        super(webActions);

    }


    public void insertHouseNumber(String houseNumber) {
        web_a.waitToBeClickable(houseNumberField).sendKeys(houseNumber);

    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);

    }

    public void clickOnNextButton() {
        web_a.waitToBeClickableAndClick(nextButton);

    }


    public Boolean currentPage() {
        if (web_a.isPresent(titleLable) != null)
            return titleLable.getText().equals("Поиск адреса вручную");
        else return false;
    }

    public void assertion(){
        Assert.assertTrue("неверный заголовок страницы",currentPage());
        Assert.assertTrue("отсутствует поле ввода адреса",addresField!=null);
        Assert.assertTrue("отсутствует кнопка возврата на предыдущее окно",backButton!=null);
    }

    public void assertionHouseNumber(){
        Assert.assertTrue("отсутствует поле ввода номера дома",houseNumberField!=null);
        Assert.assertTrue("отсутствует кнопка возврата на предыдущее окно",backButton!=null);
    }

    public void chouseAdress(int position) {

        web_a.waitToBeClickableAndClick(addresWebList.findElements(By.className("android.view.ViewGroup")).get(position));

    }
}


