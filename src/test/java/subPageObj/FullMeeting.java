package subPageObj;

import helperClasses.Swipe;
import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FullMeeting {

    WebActions web_a;

    private String toolbarTitle;
    private String requestId;
    //private String declarationType;
    private String cultureName;
    private String priceWithoutNds;
    private String volume;

    //private String myPriceWithoutLogistic;
    private Boolean logistic;
    //private String myPriceWithLogistic;
    //private String priceWithDeliverySeller;

    private Boolean prepayment;
    private String paymentDelay;

    private class messege {

        String autor;
        String price;
        String volume;

        public messege(String autor, String price, String volume) {
            this.autor = autor;
            this.price = price;
            this.volume = volume;
        }
    }

    private ArrayList messeges;

    public FullMeeting(WebActions web_a/*, SuperTest.Rols role*/) {
        this.web_a = web_a;

        WebElement toolbarTitle = web_a.isPresent(null, By.id("ru.agroclub:id/toolbarTitle"), 5);
        if (toolbarTitle != null) {
            this.toolbarTitle = toolbarTitle.getText();
        }

        WebElement requestId = web_a.isPresent(null, By.id("ru.agroclub:id/tvRequestId"), 5);
        if (requestId != null) {
            this.requestId = requestId.getText();
        }

        messeges = new ArrayList();

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

            if (textViewElements.size()<1) {

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

                        this.priceWithoutNds = textViewElements.get(1).getText().split("кг")[0].replaceAll("[^0-9.]+", "");
                        break;
                    }
                    case "Основание": {
//                    if (textViewElements.size() > 1) {
//
//
//                        this.attributes = textViewElements.get(1).getText().split("\n");
//                    }
                        break;
                    }
//                case "Адрес отгрузки": {
//
//                    this.addres = textViewElements.get(1).getText();
//                    break;
//                }
//                case "Адрес приемки": {
//
//                    this.addres = textViewElements.get(1).getText();
//                    break;
//                }
                    case "Осуществляете логистику": {

                        String text = viewElem.findElement(By.id("ru.agroclub:id/scSwitch")).getText();
                        if (text.equals("ОТКЛ."))
                            this.logistic = false;
                        else this.logistic = true;

                        break;
                    }
                    case "Условия оплаты": {

                        if (textViewElements.get(1).getText().equals("Предоплата"))
                            this.prepayment = true;
                        else {
                            this.prepayment = false;
                            this.paymentDelay = textViewElements.get(1).getText();
                        }

                        break;
                    }

                    default:
                        String text = textViewElements.get(1).getText();
                        if (text.contains("Я отправил")) {
                            messeges.add(new messege("I",
                                    web_a.getDriver().findElement(By.id("ru.agroclub:id/tvPrice")).getText().replaceAll("[^0-9,]+", ""),
                                    web_a.getDriver().findElement(By.id("ru.agroclub:id/tvVolume")).getText().replaceAll("[^0-9,]+", "")));

                        } else if (text.contains("Поставщик")) {
                            messeges.add(new messege("Поставщик",
                                    web_a.getDriver().findElement(By.id("ru.agroclub:id/tvPrice")).getText().replaceAll("[^0-9,]+", ""),
                                    web_a.getDriver().findElement(By.id("ru.agroclub:id/tvVolume")).getText().replaceAll("[^0-9,]+", "")));
                        } else if (text.contains("Закупщик")) {
                            messeges.add(new messege("Поставщик",
                                    web_a.getDriver().findElement(By.id("ru.agroclub:id/tvPrice")).getText().replaceAll("[^0-9,]+", ""),
                                    web_a.getDriver().findElement(By.id("ru.agroclub:id/tvVolume")).getText().replaceAll("[^0-9,]+", "")));
                        }
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

}
