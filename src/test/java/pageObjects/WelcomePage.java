package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class WelcomePage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/tvTitleOnBoard")
    private WebElement indicator;

    Swipe swipe;

    public WelcomePage(WebActions webActions) {
        super(webActions);
        swipe = new Swipe(webActions, 95,50,5,50, 500);

    }


    public Boolean currentPage() {
        return web_a.isPresent(indicator,5)!=null;
    }

    public void swipeWelcome(int times){
        for (int i =0; i<times; i++) {
            web_a.swipeAction(swipe);
            System.out.println("swipe");
        }
    }

    public void skipWelcomeTour(){
        if (currentPage())
            swipeWelcome(5);
    }

}


