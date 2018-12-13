package subPageObj;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FullMarcetPlaseDeclaration {

    WebActions web_a;

    private String toolbarTitle;
    private String requestId;
    private String declarationType;
    private String volume;
    private String[] attributes;
    private String cultureName;
    private String distance;
    private String nds;

    private String myPriceWithoutLogistic;
    private String myPriceWithLogistic;
    private String priceWithDeliverySeller;

    private String firstTermsOfPayment;
    private String secondTermsOfPayment;


    public FullMarcetPlaseDeclaration(WebActions web_a) {
        this.web_a = web_a;

        WebElement toolbarTitle = web_a.isPresent(null, By.id("ru.agroclub:id/toolbarTitle"), 5);
        if (toolbarTitle != null) {
            this.toolbarTitle = toolbarTitle.getText();
        }

        WebElement requestId = web_a.isPresent(null, By.id("ru.agroclub:id/tvRequestId"), 5);
//        WebElement requestId = web_a.isPresent(null, By.xpath("//*[@resourceId  = 'ru.agroclub:id/tvRequestId']"), 5);
        if (requestId != null) {
            this.requestId = requestId.getText();
        }

        WebElement declarationType = web_a.isPresent(null, By.id("ru.agroclub:id/tvDeclarationType"), 5);
        if (declarationType != null) {
            this.declarationType = declarationType.getText();
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


            WebElement volume = web_a.isPresent(viewElem, By.id("ru.agroclub:id/editText"), 2);
            if (volume != null) {

                this.volume = volume.getText();

            } else {

                List<WebElement> textViewElements = viewElem.findElements(By.className("android.widget.TextView"));

                String title = textViewElements.get(0).getText();
                switch (title) {
                    case "Культура": {
                        this.cultureName = textViewElements.get(1).getText();
                        break;
                    }
                    case "Удаленность, км": {
                        this.distance = textViewElements.get(1).getText();
                        break;
                    }
                    case "Цена, \u20BD/кг без НДС": {


                        this.myPriceWithLogistic = viewElem.findElement(By.id("ru.agroclub:id/tvLogistic")).getText();

                        WebElement nds = web_a.isPresent(viewElem, By.id("ru.agroclub:id/tvNds"), 2);
                        if (nds != null)
                            this.nds = nds.getText();

                        this.myPriceWithoutLogistic = textViewElements.get(1).getText();
                        this.priceWithDeliverySeller = textViewElements.get(4).getText();


                        break;
                    }
                    case "Показатели": {
                        if (textViewElements.size() > 1) {


                            this.attributes = textViewElements.get(1).getText().split("\n");
                        }
                        break;
                    }
                    case "Условия оплаты": {

                        this.firstTermsOfPayment = textViewElements.get(1).getText();
                        this.secondTermsOfPayment = textViewElements.get(3).getText();
                        this.secondTermsOfPayment += " " + textViewElements.get(4).getText();


                        break;
                    }


                }


            }


        }
    }

    public String getToolbarTitle() {
        return toolbarTitle;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getDeclarationType() {
        return declarationType;
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

    public String getDistance() {
        return distance;
    }

    public String getNds() {
        return nds;
    }

    public String getMyPriceWithoutLogistic() {
        return myPriceWithoutLogistic;
    }

    public String getMyPriceWithLogistic() {
        return myPriceWithLogistic;
    }

    public String getPriceWithDeliverySeller() {
        return priceWithDeliverySeller;
    }

    public String getFirstTermsOfPayment() {
        return firstTermsOfPayment;
    }

    public String getSecondTermsOfPayment() {
        return secondTermsOfPayment;
    }
}
