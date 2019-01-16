package superClasses;

import helperClasses.WebActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public abstract class MainAgroPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/navigation_menu")
    private WebElement menuButton;

    @FindBy(id = "ru.agroclub:id/navigation_weather")
    private WebElement weatherButton;


    @FindBy(id = "ru.agroclub:id/navigation_create_request")
    private WebElement createButton;


    @FindBy(id = "ru.agroclub:id/navigation_my_requests")
    private WebElement myRequestsButton;


    @FindBy(id = "ru.agroclub:id/navigation_market")
    private WebElement marketButton;


    public MainAgroPage(WebActions webActions) {
        super(webActions);
    }


//    public Boolean currentPage() {
//        return titleLabel.getText().equals("Рынок");
//    }

    public void clickOnMenuButton(){
        web_a.waitToBeClickableAndClick(menuButton);
    }

    public void clickOnWeatherButton(){
        web_a.waitToBeClickableAndClick(weatherButton);
    }

    public void clickOnCreateButton(){
        web_a.waitToBeClickableAndClick(createButton);
    }

    public void clickOnMyRequestButton(){
        web_a.waitToBeClickableAndClick(myRequestsButton);
    }

    public void clickOnMarketButton(){
        web_a.waitToBeClickableAndClick(marketButton);
    }

}


