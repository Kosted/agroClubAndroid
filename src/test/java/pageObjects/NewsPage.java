package pageObjects;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.NewsCard;
import subPageObj.ShortMarcetPlaseDeclaration;

import java.util.List;

public class NewsPage extends MainAgroPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/recycler")
    private WebElement newsWebList;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;


    private Swipe swipedeclarationsDown;

    public NewsPage(WebActions webActions) {
        super(webActions);
        swipedeclarationsDown = new Swipe(web_a, 50, 73, 50, 45, 200);
    }


    public Boolean currentPage() {
        return web_a.isPresent(titleLabel).getText().equals("Новости");
    }


    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }

    public NewsCard chouseNews(int position) {

        List<WebElement> elements = newsWebList.findElements(By.className("android.view.ViewGroup"));
        if (elements.size() > position) {
            WebElement newsWebElem = elements.get(position);

            NewsCard newsCard = new NewsCard(
                    newsWebElem.findElement(By.id("ru.agroclub:id/tvDate")).getText(),
                    newsWebElem.findElement(By.id("ru.agroclub:id/tvTitle")).getText(),
                    newsWebElem.findElement(By.id("ru.agroclub:id/tvDescription")).getText(),
                    null,
                    null);

            elements.get(position).click();
            newsCard.setDetailDate(driver.findElement(By.id("ru.agroclub:id/tvDate")).getText());
            newsCard.setDetailTitle(driver.findElement(By.id("ru.agroclub:id/tvTitle")).getText());
            newsCard.setText(driver.findElement(By.id("ru.agroclub:id/tvText")).getText());

            web_a.swipeAction(swipedeclarationsDown);
            web_a.swipeAction(swipedeclarationsDown);

            newsCard.setSource(driver.findElement(By.id("ru.agroclub:id/tvSource")).getText());
            return newsCard;
        }
        return null;
    }

    public boolean dateCompare(String firstDate, String secondDate) {
        String[] first= firstDate.split("\\.");
        String[] second = secondDate.split("\\.");

        if (Integer.parseInt(first[2]) > Integer.parseInt(second[2]))
            return true;
        else if (Integer.parseInt(first[2]) < Integer.parseInt(second[2]))
            return false;
        else

        if (Integer.parseInt(first[1]) > Integer.parseInt(second[1]))
            return true;
        else if (Integer.parseInt(first[1]) < Integer.parseInt(second[1]))
            return false;
        else

        if (Integer.parseInt(first[0]) > Integer.parseInt(second[0]))
            return true;
        else if (Integer.parseInt(first[0]) < Integer.parseInt(second[0]))
            return false;
        else return true;
    }


}


