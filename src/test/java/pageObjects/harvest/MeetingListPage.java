package pageObjects.harvest;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import subPageObj.FullMyDeclaration;
import superClasses.SuperPage;

import java.util.List;

public class MeetingListPage extends SuperPage {

    private final Swipe swipedPropertisDown;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    public MeetingListPage(WebActions webActions) {
        super(webActions);
        swipedPropertisDown = new Swipe(web_a, 50, 73, 50, 45, 600);
    }



    public Boolean currentPage() {
        return web_a.isPresent(toolBarTitle).getText().contains("Предложения");
    }


    public FullMyDeclaration chouseMeeting(int position) {

        List<WebElement> elements = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout"));
        if (elements.size() > position) {

            elements.get(position).click();
            FullMyDeclaration fullMyDeclaration = new FullMyDeclaration(web_a);


            // clickOnBackButton();

            return fullMyDeclaration;
        }
        return null;
    }

    private void swipeProperty() {
        web_a.swipeAction(swipedPropertisDown);
        System.out.println("swipeProperty");
    }

//    public Boolean currentPage() {
//        return web_a.isPresent(toolBarTitle).getText().equals("Логистика");
//
//    }

    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);

    }

}


