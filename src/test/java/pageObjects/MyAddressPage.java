package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

import java.util.List;

public class MyAddressPage extends SuperPage {

    private final Swipe swipeMyAddressListDown;
    private final Swipe swipeMyAddressListUp;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    @FindBy(id = "ru.agroclub:id/btnNext")
    private WebElement nextButton;

    @FindBy(id = "ru.agroclub:id/btnAddAddress")
    private WebElement addNewAddresButton;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    public MyAddressPage(WebActions webActions) {
        super(webActions);
        swipeMyAddressListDown = new Swipe(web_a, 50, 73, 50, 45, 1000);
        swipeMyAddressListUp = new Swipe(web_a, 50, 45, 50, 73, 200);

    }

    public void assertion() {
        Assert.assertTrue("отсутствует заголовок страницы \"Укажите адрес отгрузки\"", currentPage(30));
        Assert.assertNotNull("отсутствует кнопка далее", nextButton);
    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public Boolean currentPage(int waitTime) {
        if (web_a.isPresent(toolBarTitle, waitTime) != null)
            return toolBarTitle.getText().equals("Мои адреса");
        else return false;
    }


    public Integer getMyAddresListSize() {
        return web_a.getDriver().findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup")).size();
    }

    private WebElement getMyAddres(int position) {
//        while (web_a.isPresent(null, By.id("ru.agroclub:id/recycler"), 3)==null){
//            System.out.println("не могу найти recycler");
//
//        }
//            //web_a.isPresent(null, By.id("ru.agroclub:id/recycler"), 3);
        List<WebElement> elements = web_a.getDriver().findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));
        while (elements.size() == 0)
            elements = web_a.getDriver().findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));
        return elements.get(position);
    }

    public String getMyAddresName(int position) {
        return getMyAddres(position).findElement(By.id("ru.agroclub:id/tvName")).getText();
    }

    public String getMyAddresAddres(int position) {
        return getMyAddres(position).findElement(By.id("ru.agroclub:id/tvAddress")).getText();
    }

    public Boolean getMyAddresUpdateIsPossible(int position) {
        return web_a.isPresent(getMyAddres(position), By.id("ru.agroclub:id/ivMore"), 3) != null;
    }

    public void setMyAddresMain(int position) {
        Boolean myAddresUpdateIsPossible = getMyAddresUpdateIsPossible(position);
        if (myAddresUpdateIsPossible) {
            web_a.isPresent(getMyAddres(position), By.id("ru.agroclub:id/ivMore"), 3).click();
            web_a.isPresent(null, By.id("android:id/title"), 3).click();

        }
    }

    public void clickOnAddAddresButton() {
        while (web_a.isPresent(addNewAddresButton, 2) == null)
            web_a.swipeAction(swipeMyAddressListDown);
        web_a.waitToBeClickableAndClick(addNewAddresButton);

    }
    public void chouseAddres(int position) {
        web_a.waitToBeClickableAndClick(getMyAddres(position));

    }

    public void swipeUp() {
        web_a.swipeAction(swipeMyAddressListUp);
        web_a.swipeAction(swipeMyAddressListUp);
    }

}


