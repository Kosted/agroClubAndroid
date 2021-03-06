package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.MainAgroPage;

import java.util.List;

public class MarketFilterPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/rvDeclarations")
    private WebElement declarationWebList;

    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement doOfferButton;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/clAddress")
    private WebElement chouseAddresButton;

    @FindBy(id = "ru.agroclub:id/ivFilter")
    private WebElement filterButton;

    private Swipe swipedeclarationsDown;

    public MarketFilterPage(WebActions webActions) {
        super(webActions);
        swipedeclarationsDown = new Swipe(web_a, 50, 73, 50, 45, 1000);
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public FullMarcetPlaseDeclaration chouseDeclaration(int position, Boolean doOffer) {

        List<WebElement> elements = declarationWebList.findElements(By.className("android.view.ViewGroup"));
        if (elements.size() > position) {

            elements.get(position).click();
            FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration = new FullMarcetPlaseDeclaration(web_a);

            if (doOffer) {
                web_a.waitToBeClickableAndClick(doOfferButton);
            } else
                clickOnBackButton();

            return fullMarcetPlaseDeclaration;
        }
        return null;
    }

    public ShortMarcetPlaseDeclaration getShortMarcetPlaseDeclaration(int position) {
        List<WebElement> elements = declarationWebList.findElements(By.className("android.view.ViewGroup"));
        if (elements.size() > position)
            return new ShortMarcetPlaseDeclaration(web_a, elements.get(position));
        else {
            System.out.println("в списке нет предложения с позицией " + position);
            return null;
        }
    }

    public Integer getDeclarationListSize(){
        return declarationWebList.findElements(By.className("android.view.ViewGroup")).size();
    }


    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void clickOnFilterButton() {
        web_a.waitToBeClickableAndClick(filterButton);
    }

    public void clickOnChouseAddresButton() {
        web_a.waitToBeClickableAndClick(chouseAddresButton);
    }

    public String getMarketAddres() {
        WebElement present = web_a.isPresent(chouseAddresButton, By.id("ru.agroclub:id/tvData2"), 3);
        if (present != null)
            return present.getText();
        else return null;
    }

}


