package pageObjects.harvest;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class DealsPage extends SuperPage {

    private final Swipe swipedPropertisDown;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement toolBarTitle;

    public DealsPage(WebActions webActions) {
        super(webActions);
        swipedPropertisDown = new Swipe(web_a, 50, 73, 50, 45, 600);
    }



    public Boolean currentPage() {
        return web_a.isPresent(toolBarTitle,20).getText().contains("Сделка ID");
    }


//    public void clickOnPropertyField(String fieldName) {
//
//        List<WebElement> fieldsList;
//        String currentFieldsName = "";
//        int swipeCount = 5;
//
//        while (!currentFieldsName.contains(fieldName) && swipeCount > 0) {
//
//            fieldsList = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));
//
//            for (WebElement webElement : fieldsList) {
//                if (web_a.isPresent(webElement, By.className("android.widget.EditText"), 1) != null)
//                    currentFieldsName = web_a.isPresent(webElement, By.className("android.widget.EditText"), 1).getText();
//                else
//                    currentFieldsName = webElement.findElement(By.className("android.widget.TextView")).getText();
//
//                if (currentFieldsName.contains(fieldName)) {
//                    webElement.click();
//                    return;
//
//                }
//            }
//            if (swipeCount > 0)
//                swipeProperty();
//            else return;
//            swipeCount--;
//        }
//
//        return;
//    }

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


