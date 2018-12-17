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
        swipeCultureRight = new Swipe(web_a, 90, 17, 10, 17, 1000);
        swipeCultureLeft = new Swipe(web_a, 10, 17, 90, 17, 1000);
        swipedeclarationsDown = new Swipe(web_a, 50, 73, 50, 45, 1000);
    }


    public Boolean currentPage() {
        return web_a.isPresent(titleLabel).getText().equals("Рынок");
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

    public String chouseCulturefilter(String cultureName) {

        List<WebElement> cultureList;
        String currentfilter = "";

        while (!currentfilter.equals(cultureName)) {

            cultureList = getVisableCulture();

            for (WebElement webElement : cultureList) {

                currentfilter = webElement.getText();

                if (currentfilter.equals(cultureName)) {
                    webElement.click();
                    return cultureName;

                }
            }
            swipeСulture("right");
        }

        return cultureName;
    }

    public List<WebElement> getVisableCulture() {
//if (web_a.isPresent(null, By.id("ru.agroclub:id/rvCrop"),5)!= null))
        if (currentPage())
        return cultureFilterWebList.findElements(By.className("android.widget.TextView"));
else
    return null;
    }

    public void swipeСulture(String direction) {
        switch (direction) {
            case "right": {
                web_a.swipeAction(swipeCultureRight);
                break;
            }
            case "left": {
                web_a.swipeAction(swipeCultureRight);
                break;
            }
        }
        System.out.println("swipe " + direction);
    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public void clickOnchouseAddresButton() {
        web_a.waitToBeClickableAndClick(chouseAddresButton);
    }

    public String getMarketAddres() {
        WebElement present = web_a.isPresent(chouseAddresButton, By.id("ru.agroclub:id/tvData2"), 3);
        if (present != null)
            return present.getText();
        else return null;
    }

}


