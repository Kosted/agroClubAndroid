package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;

import java.util.List;

public class MarketResponsePage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;


    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement doOfferButton;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;


    Swipe swipedeclarationsDown;

    public MarketResponsePage(WebActions webActions) {
        super(webActions);
        swipedeclarationsDown = new Swipe(web_a, 50,73,50,45,1000);
    }


    public Boolean currentPage() {
        return web_a.isPresent(titleLabel).getText().equals("Рынок");
    }

    public void clickOnBackButton(){
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void clickOnDoOfferButton(){
        web_a.swipeAction(swipedeclarationsDown);

        web_a.waitToBeClickableAndClick(doOfferButton);
    }

}


