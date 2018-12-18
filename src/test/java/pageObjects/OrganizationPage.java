package pageObjects;

import helperClasses.WebActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import superClasses.SuperPage;

public class OrganizationPage extends SuperPage {

    @FindBy(id = "ru.agroclub:id/toolbarTitle")
    private WebElement titleLabel;

    @FindBy(id = "ru.agroclub:id/toolbarBackBtn")
    private WebElement backButton;




    String organizationName;
    String inn;
    String kpp;
    String ogrn;
    String mainAdress;
    String generalDirector;
    String nds;


    public OrganizationPage(WebActions webActions) {
        super(webActions);
    }


    public Boolean currentPage(int waitTime) {
        if (web_a.isPresent(titleLabel, waitTime) != null)
            return titleLabel.getText().equals("Организация");
        else return false;
    }



    public void clickOnBackButton() {
        web_a.waitToBeClickableAndClick(backButton);
    }


    public void fullOrganizationInfo() {

        WebElement organizationName = web_a.isPresent(null, By.id("ru.agroclub:id/tvOrganizationName"), 20);
        if (organizationName != null) {
            this.organizationName = organizationName.getText();
        }
        WebElement kpp = web_a.isPresent(null, By.id("ru.agroclub:id/tvKpp"), 20);
        if (kpp != null) {
            this.kpp = kpp.getText().split(" ")[1];
        }

        WebElement inn = web_a.isPresent(null, By.id("ru.agroclub:id/tvInn"), 5);
        if (inn != null) {
            this.inn = inn.getText().split(" ")[1];
        }

        WebElement ogrn = web_a.isPresent(null, By.id("ru.agroclub:id/tvOgrn"), 5);
        if (ogrn != null) {
            this.ogrn = ogrn.getText().split(" ")[1];
        }
        WebElement generalDirector = web_a.isPresent(null, By.id("ru.agroclub:id/tvHeadPosition"), 5);
        if (generalDirector != null) {
            this.generalDirector = generalDirector.getText().split("Генеральный директор ")[1];
        }

        WebElement nds = web_a.isPresent(null, By.id("ru.agroclub:id/tvVat"), 5);
        if (nds != null) {
            this.nds = nds.getText().split(" ")[1];
        }

        WebElement mainAdress = web_a.isPresent(null, By.id("ru.agroclub:id/tvAddress"), 5);
        if (mainAdress != null) {
            this.mainAdress = mainAdress.getText().split("Основной адрес организации ")[1];
        }

    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getInn() {
        return inn;
    }

    public String getMainAdress() {
        return mainAdress;
    }

    public String getNds() {
        return nds;
    }

    public String getKpp() {
        return kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getGeneralDirector() {
        return generalDirector;
    }

    public void assertion() {
        Assert.assertTrue("отсутствует поле с названием организации",organizationName!=null);
        Assert.assertTrue("отсутствует поле с инн",inn!=null);
        Assert.assertTrue("отсутствует поле кпп",kpp!=null);
        Assert.assertTrue("отсутствует поле оргн",ogrn!=null);
        Assert.assertTrue("отсутствует поле с андресом",mainAdress!=null);
        Assert.assertTrue("отсутствует с именем ген деректора ",generalDirector!=null);
        Assert.assertTrue("отсутствует поле с ндс ",nds!=null);
    }
}


