package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;

import java.util.List;

public class MarcetplacePage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/rvCrop")
    private WebElement cultureFilterWebList;

    @FindBy(id = "ru.agroclub:id/rvDeclarations")
    private WebElement declarationWebList;

    @FindBy(id = "ru.agroclub:id/btnBottom")
    private WebElement doOfferButton;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/clAddress")
    private WebElement chouseAddresButton;

    private Swipe swipeCultureRight;
    private Swipe swipeCultureLeft;
    private Swipe swipedeclarationsDown;

    public MarcetplacePage(WebActions webActions) {
        super(webActions);
        swipeCultureRight = new Swipe(web_a, 90,17,10,17,1000);
        swipeCultureLeft = new Swipe(web_a, 10,17,90,17,1000);
        swipedeclarationsDown = new Swipe(web_a, 50,73,50,45,1000);
    }


    public Boolean currentPage() {
        return web_a.isPresent(titleLabel).getText().equals("Рынок");
    }

    public FullMarcetPlaseDeclaration chouseDeclaration(int position, Boolean doOffer) {
        declarationWebList.findElements(By.className("android.view.ViewGroup")).get(position).click();
        if (doOffer) {
            FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration = new FullMarcetPlaseDeclaration(web_a);
            web_a.waitToBeClickableAndClick(doOfferButton);
            return fullMarcetPlaseDeclaration;
        }else
            return new FullMarcetPlaseDeclaration(web_a);
    }

    public ShortMarcetPlaseDeclaration getShortMarcetPlaseDeclaration(int position) {
        List<WebElement> elements = declarationWebList.findElements(By.className("android.view.ViewGroup"));
        return new ShortMarcetPlaseDeclaration(web_a, elements.get(position));
    }

    public String chouseCulturefilter(String cultureName) {

        List<WebElement> cultureList;
        String currentfilter = "";

        while (!currentfilter.equals(cultureName)) {

            cultureList = cultureFilterWebList.findElements(By.className("android.widget.TextView"));

            for (WebElement webElement : cultureList) {

                currentfilter = webElement.getText();

                if (currentfilter.equals(cultureName)) {
                    webElement.click();
                    return cultureName;

                }
            }
        web_a.swipeAction(swipeCultureRight);
        }

        return cultureName;
    }

    public void swipeTest(){

        web_a.waitToBeClickable(cultureFilterWebList.findElements(By.className("android.view.ViewGroup")).get(0));

        web_a.swipeAction(swipeCultureRight);
        web_a.swipeAction(swipeCultureRight);

        web_a.swipeAction(swipeCultureLeft);
        web_a.swipeAction(swipeCultureLeft);

        web_a.swipeAction(swipedeclarationsDown);

        web_a.swipeAction(swipedeclarationsDown);



    }

    public void clickOnBackButton(){
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void clickOnchouseAddresButton(){
        web_a.waitToBeClickableAndClick(chouseAddresButton);
    }

    public String getMarketAddres(){
        WebElement present = web_a.isPresent(chouseAddresButton, By.id("ru.agroclub:id/tvData2"), 3);
        if (present!=null)
            return present.getText();
        else return null;
    }

}


