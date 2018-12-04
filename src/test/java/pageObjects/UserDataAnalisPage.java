package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

import java.util.List;

public class UserDataAnalisPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "app")
    private WebElement textWall;


    public UserDataAnalisPage(WebActions webActions) {
        super(webActions);
    }


    public Boolean currentPage() {
        return web_a.isPresent(titleLabel, 30) != null;
    }

    public Boolean isDocumentEmpty() {
        List<WebElement> dockument = textWall.findElement(By.className("android.view.View")).findElements(By.className("android.view.View"));
        for (int i = 0; i < 3; i++) {

            if (dockument.get(i).getText().equals(""))
                return true;
        }
        return false;
    }

    public void clickOnBackButton() {
        web_a.waitToBeClickable(backButton).click();
    }
}


