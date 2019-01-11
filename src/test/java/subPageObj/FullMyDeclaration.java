package subPageObj;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import superClasses.SuperTest;

import java.util.List;

public class FullMyDeclaration {

    WebActions web_a;

    private String toolbarTitle;
    private String requestId;
    //private String declarationType;
    private String volume;
    private String[] attributes;
    private String cultureName;
    //private String nds;

    //private String myPriceWithoutLogistic;
    private String priceWithoutNds;
    private Boolean logistic;
    //private String myPriceWithLogistic;
    //private String priceWithDeliverySeller;

    private Boolean prepayment;
    private String paymentDelay;
    private String addres;


    public FullMyDeclaration(WebActions web_a/*, SuperTest.Rols role*/) {
        this.web_a = web_a;

        WebElement toolbarTitle = web_a.isPresent(null, By.id("ru.agroclub:id/toolbarTitle"), 5);
        if (toolbarTitle != null) {
            this.toolbarTitle = toolbarTitle.getText();
        }

        WebElement requestId = web_a.isPresent(null, By.id("ru.agroclub:id/tvRequestId"), 5);
        if (requestId != null) {
            this.requestId = requestId.getText();
        }

        searchAllElem();
        web_a.swipeAction(new Swipe(web_a, 50, 75, 50, 10, 200));
        searchAllElem();


    }

    public void searchAllElem() {

        WebElement recycler = web_a.isPresent(null, By.xpath("//android.support.v7.widget.RecyclerView"), 10);

        // List<WebElement> recyclerListView = recycler.findElements(By.xpath("/child::android.view.ViewGroup"));
        List<WebElement> recyclerListView = recycler.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup"));

        for (int i = 0; i < recyclerListView.size(); i++) {

            WebElement viewElem = recyclerListView.get(i);//.findElement(By.className("android.view.ViewGroup"));

            List<WebElement> textViewElements = viewElem.findElements(By.className("android.widget.TextView"));

            String title = textViewElements.get(0).getText();
            switch (title) {
                case "Культура": {
                    this.cultureName = textViewElements.get(1).getText();
                    break;
                }
                case "Объем, т": {
                    this.volume = textViewElements.get(1).getText();
                    break;
                }
                case "Цена, \u20BD/кг без НДС": {

                    this.priceWithoutNds = textViewElements.get(1).getText();
                    break;
                }
                case "Показатели": {
                    if (textViewElements.size() > 1) {


                        this.attributes = textViewElements.get(1).getText().split("\n");
                    }
                    break;
                }
                case "Адрес отгрузки": {

                    this.addres = textViewElements.get(1).getText();
                    break;
                }
                case "Адрес приемки": {

                    this.addres = textViewElements.get(1).getText();
                    break;
                }
                case "Осуществляете логистику": {

                    String text = viewElem.findElement(By.id("ru.agroclub:id/scSwitch")).getText();
                    if (text.equals("ОТКЛ."))
                        this.logistic = false;
                    else this.logistic = true;

                    break;
                }
                case "Условия оплаты": {

                    if (textViewElements.get(1).getText().split(",")[0].equals("Предоплата"))
                        this.prepayment = true;
                    else
                        this.prepayment = false;

                    this.paymentDelay = textViewElements.get(1).getText().split(",")[1];
                    break;
                }
            }
        }
    }

    public Boolean getLogistic() {
        return logistic;
    }

    public String getToolbarTitle() {
        return toolbarTitle;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getVolume() {
        return volume;
    }

    public Integer getAttribute(String attributeName) {
        for (String attributeElem : attributes)
            if (attributeElem.contains(attributeName))
                return Integer.parseInt(attributeElem.replaceAll("[^0-9]+", ""));
        return null;
    }

    public String getCultureName() {
        return cultureName;
    }

    public String getPriceWithoutNds() {
        return priceWithoutNds;
    }

    public Boolean getPrepayment() {
        return prepayment;
    }

    public String getPaymentDelay() {
        return paymentDelay;
    }

    public String getAddres() {
        return addres;
    }
}
