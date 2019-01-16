package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMyDeclaration;
import superClasses.MainAgroPage;

import java.util.List;

public class MyDeclarationPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement declarationWebList;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    private Swipe swipeDeclarationsDown;

    public MyDeclarationPage(WebActions webActions) {
        super(webActions);
        swipeDeclarationsDown = new Swipe(web_a, 50, 73, 50, 45, 1000);
    }


    public Boolean currentPage() {
        return web_a.isPresent(titleLabel).getText().contains("Мои заявки");
    }

    public FullMyDeclaration chouseDeclaration(int position) {

        List<WebElement> elements = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout"));
        if (elements.size() > position) {

            elements.get(position).click();
            FullMyDeclaration fullMyDeclaration = new FullMyDeclaration(web_a);


            // clickOnBackButton();

            return fullMyDeclaration;
        }
        return null;
    }

    public void chouseDeclarationOffers(int position) {

        List<WebElement> elements = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout"));
        if (elements.size() > position)

            elements.get(position).findElement(By.id("ru.agroclub:id/clOffers")).click();

    }

    public void chouseDeclarationMeetings(int position) {

        List<WebElement> elements = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout"));
        if (elements.size() > position)

            elements.get(position).findElement(By.id("ru.agroclub:id/clMeetings")).click();

    }

    public void chouseDeclarationDeals(int position) {

        List<WebElement> elements = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout"));
        if (elements.size() > position)

            elements.get(position).findElement(By.id("ru.agroclub:id/clDeal")).click();

    }

//    public ShortMarcetPlaseDeclaration getShortMarcetPlaseDeclaration(int position) {
//        List<WebElement> elements = declarationWebList.findElements(By.className("android.view.ViewGroup"));
//        if (elements.size() > position)
//            return new ShortMarcetPlaseDeclaration(web_a, elements.get(position));
//        else {
//            System.out.println("в списке нет предложения с позицией " + position);
//            return null;
//        }
//    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }


    public void chousFarmerMarketplase(MarketSections marketSections) {
        String locator = "ru.agroclub:id/cv";
        switch (marketSections) {
            case HARVEST:
                locator += "Harvest";
                break;
            case SEEDS:
                locator += "Seeds";
                break;
            case SZR:
                locator += "SZR";
                break;
            case FERTILIZER:
                locator += "Fertilizer";
                break;
        }
        web_a.isPresent(null, By.id(locator), 5).click();
    }

    private void swipeDeclarationsDown(int times) {
        while (times > 0) {
            web_a.swipeAction(swipeDeclarationsDown);
            System.out.println("swipeDeclarationsDown");
            times--;
        }
    }

    public enum MarketSections {HARVEST, SEEDS, SZR, FERTILIZER, COUNTERFEIT}

}


