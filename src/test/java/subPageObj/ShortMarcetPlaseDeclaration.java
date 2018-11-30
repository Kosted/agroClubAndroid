package subPageObj;

import helperClasses.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShortMarcetPlaseDeclaration {

    private String declarationType;
    private String priceWithDeliverySeller;
    private String priceSign;
    private String cultureName;
    private String mainAttribute;
    private String volume;
    private String distance;
    private String priceSeller;

    public ShortMarcetPlaseDeclaration(WebActions web_a, WebElement shortMarcetPlaseDeclaration) {


        WebElement declarationType = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvDeclarationType"), 5);
        if (declarationType != null) {
            this.declarationType = declarationType.getText();
        }

        WebElement priceWithDeliverySeller = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvPriceWithDeliverySeller"), 5);
        if (priceWithDeliverySeller != null) {
            this.priceWithDeliverySeller = priceWithDeliverySeller.getText();
        }

        WebElement priceSign = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvPriceSign"), 5);
        if (priceSign != null) {
            this.priceSign = priceSign.getText();
        }

        WebElement cultureName = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvName"), 5);
        if (cultureName != null) {
            this.cultureName = cultureName.getText();
        }

        WebElement mainAttribute = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvAttribute"), 5);
        if (mainAttribute != null) {
            this.mainAttribute = mainAttribute.getText();
        }

        WebElement volume = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvVolume"), 5);
        if (volume != null) {
            this.volume = volume.getText();
        }

        WebElement distance = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvDistance"), 5);
        if (distance != null) {
            this.distance = distance.getText();
        }

        WebElement priceSeller = web_a.isPresent(shortMarcetPlaseDeclaration, By.id("ru.agroclub:id/tvPriceSeller"), 5);
        if (priceSeller != null) {
            this.priceSeller = priceSeller.getText();
        }


    }

    public String getDeclarationType() {
        return declarationType;
    }

    public String getPriceWithDeliverySeller() {
        return priceWithDeliverySeller;
    }

    public String getPriceSign() {
        return priceSign;
    }

    public String getCultureName() {
        return cultureName;
    }

    public String getMainAttribute() {
        return mainAttribute;
    }

    public String getVolume() {
        return volume;
    }

    public String getDistance() {
        return distance;
    }

    public String getPriceSeller() {
        return priceSeller;
    }
}
