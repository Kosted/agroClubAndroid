//package pageObjects;
//
//import helperClasses.Swipe;
//import helperClasses.WebActions;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//import java.util.List;
//
//public class MarketChousFilterPage extends MainAgroPage {
//
//    @FindBy(id = "ru.agroclub:id/toolbarTitle")
//    private WebElement titleLabel;
//
//    @FindBy(id = "ru.agroclub:id/recycler")
//    private WebElement filtersWebList;
//
//    @FindBy(id = "ru.agroclub:id/toolbarLeftBtn")
//    private WebElement defaultFiltersButton;
//
//    @FindBy(id = "ru.agroclub:id/toolbarRightBtn")
//    private WebElement closeFiltersButton;
//
//    @FindBy(id = "ru.agroclub:id/btn")
//    private WebElement confirmButton;
//
//    private Swipe swipedFiltersDown;
//
//    public MarketChousFilterPage(WebActions webActions) {
//        super(webActions);
//        swipedFiltersDown = new Swipe(web_a, 50,73,50,45,1000);
//    }
//
//    public String getTitle(){
//        return titleLabel.getText();
//    }
//
//
//
//    public void clickOnConfirmButton() {
//        while (web_a.isPresent(confirmButton, 2) == null)
//            web_a.swipeAction(swipedFiltersDown);
//        web_a.waitToBeClickableAndClick(confirmButton);
//
//    }
//
//
//
//    public void clickOnCloseFiltersButton(){
//        web_a.waitToBeClickableAndClick(closeFiltersButton);
//    }
//
//    public void clickOnDefaultFilterButton(){
//        web_a.waitToBeClickableAndClick(defaultFiltersButton);
//    }
//
//    public String setFilter(String property,String value, Integer position){
//        if (property==null){
//
//        }else{
//
//
//
//        }
//
//    }
//
//    public WebElement getPropertyField(String fieldName) {
//
//        List<WebElement> fieldsList;
//        String currentFieldsName = "";
//
//        while (!currentFieldsName.equals(fieldName)) {
//
//            fieldsList = filtersWebList.findElements(By.className("android.widget.TextView"));
//
//            for (WebElement webElement : fieldsList) {
//
//                currentFieldsName = webElement.getText();
//
//                if (currentFieldsName.equals(fieldName)) {
//                    return webElement;
//
//                }
//            }
//            swipeProperty();
//        }
//
//        return null;
//    }
//
//    private void swipeProperty() {
//        web_a.swipeAction(swipedFiltersDown);
//    }
//
//
//}
//
//
