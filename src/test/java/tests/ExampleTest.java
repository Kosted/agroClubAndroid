package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.CreateDeclarationPropertyPage;
import pageObjects.MyDeclarationPage;
import pageObjects.harvest.DealsPage;
import pageObjects.harvest.HarvestBuyConditionPage;
import pageObjects.MarcetplacePage;
import pageObjects.harvest.MarketResponsePage;
import subPageObj.FullMeeting;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;


import static pageObjects.CreateDeclarationPropertyPage.Sign.MORE;
import static pageObjects.harvest.HarvestBuyConditionPage.PaymentDelay.WITOUTDELAY;
import static pageObjects.harvest.MarketResponsePage.paymentCondition.PREPAYMENT;
import static pageObjects.harvest.MarketResponsePage.whoDelivers.I;
import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class ExampleTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER, "Лен", "10", "10", "Масличность", "10", MORE, true, true, WITOUTDELAY}

        });
    }

    private Rols role;
    private String culture;
    private String price;
    private String volume;
    private String property;
    private String propertyValue;
    private CreateDeclarationPropertyPage.Sign propertySign;
    private Boolean logistic;
    private Boolean prepayment;
    private HarvestBuyConditionPage.PaymentDelay paymentDelay;

    public ExampleTest(Rols role, String culture, String price, String volume, String property, String propertyValue, CreateDeclarationPropertyPage.Sign propertySign, Boolean logistic, Boolean prepayment, HarvestBuyConditionPage.PaymentDelay paymentDelay) {
        this.role = role;
        this.culture = culture;
        this.price = price;
        this.volume = volume;
        this.property = property;
        this.propertyValue = propertyValue;
        this.propertySign = propertySign;
        this.logistic = logistic;
        this.prepayment = prepayment;
        this.paymentDelay = paymentDelay;
    }

    @Test
    public void exampleTest() {

        // создание заявки на продажу фермера
        autorization("8000000001", role);
        //priceUpdatePage.clickOnConfirmButton();


        createDeclaration();

        marcetplacePage.clickOnBackButton();

        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();

// создание заявки закупщика и создание отклика на заявку фермера

        // создание заявки
        role = PURCHASER;
        price = "20";
        volume = "20";
        propertyValue = "20";

        autorization("8000000002", role);

        createDeclaration();

        marcetplacePage.clickOnBackButton();

        // отклик закупщика на заявку фермера

        price = "21";
        volume = "21";
        propertyValue = "21";

        marcetplacePage.clickOnMarketButton();

        marcetplacePage.chouseCulturefilter(culture);

        marketFilterPage.chouseDeclaration(0, true);

        marcetResponcePage.setVolume(price);
        marcetResponcePage.chouseDelivary(I);
        marcetResponcePage.chousePaymentCondition(PREPAYMENT);
        marcetResponcePage.clickOnDoOfferButton();


        //проверки создавшегося отклика

        //

        meetingPage.clickOnBackButton();
        meetingListPage.clickOnBackButton();

        marcetplacePage.clickOnMenuButton();
        menuPage.clickOnLogautButton();

// создание отклика фермера на заявку закупщика
        role = FARMER;
        price = "11";
        volume = "11";
        propertyValue = "11";

        autorization("8000000001", role);
        //принятие переговоров

        marcetplacePage.clickOnMyRequestButton();

        myDeclarationPage.chousFarmerMarketplase(MyDeclarationPage.MarketSections.HARVEST);

        myDeclarationPage.chouseDeclarationMeetings(0);

        meetingListPage.chouseMeeting(0);

        FullMeeting fullMeeting = new FullMeeting(web_a);

        // проверка на правлиность составления переговоров
        //TO DO

        meetingPage.clickOnAcceptButton();

        // проверка на правильность составления сделки
        // TO DO
        dealsPage.currentPage();
        dealsPage.clickOnBackButton();

        meetingListPage.clickOnBackButton();

        myDeclarationPage.chouseDeclaration(0, MyDeclarationPage.Action.DELETE);

        marcetplacePage.chousFarmerMarketplase(MarcetplacePage.MarketSections.HARVEST);

        marcetplacePage.clickOnMarketButton();

        marcetplacePage.chouseCulturefilter("Лен");

        marketFilterPage.chouseDeclaration(1, true);

        marcetResponcePage.setVolume(price);
        marcetResponcePage.chouseDelivary(I);
        marcetResponcePage.chousePaymentCondition(PREPAYMENT);
        marcetResponcePage.clickOnDoOfferButton();

        //проверки создавшегося отклика

        //
    }

    /*залониться под фермером
     * создание фермером заявки
     * разлониться
     *
     *
     * залогиниться под закупщиком
     * создание закупщиком заявки
     *
     * формирование отклика на заявку фермера с новым объемом
     *
     * проверка правильности составления отклика.
     *
     * убедиться что по отклику была создана новая заявка у закупщика
     *
     * закрыть 1 свою заявку
     *
     * разлониться
     *
     *
     * залогиниться под фермером
     *
     * принять переговоры
     *
     * проверить правильность составленной сделки
     *
     * создать отклик на заявку закупщика с новым объемом.
     *
     * проверить правильность созданного отклика
     *
     * убедиться что по отклику была создана новая заявка
     *
     * закрыть свою заявку
     *
     * разлогиниться
     *
     *
     * залогиниться под закупщиком
     *
     * принять переговоры
     *
     * убедиться в правильности составления предыдущей сделки
     *
     * убедиться в правильности составления сделки принятой закупщиком
     *
     *
     * */

    private void createDeclaration() {
        marcetplacePage.clickOnCreateButton();

        if (role.equals(FARMER))
            createNewDeclarationPage.farmerChousDeclarationType(MarcetplacePage.MarketSections.HARVEST);

        createNewDeclarationPage.setField("Выберите культуру", null);

        chousListPage.clickOnPropertyField(culture);

        createNewDeclarationPage.setField("Цена", price);
        createNewDeclarationPage.setField("Объем", volume);

        createNewDeclarationPage.setField("показатели", null);

        createDeclarationPropertyPage.setProperty(property, propertyValue, propertySign);

        createDeclarationPropertyPage.clickOnConfirmButton();

        createNewDeclarationPage.setField("Условия оплаты", null);

        harvestBuyConditionPage.clickOnConfirmButton();

        createNewDeclarationPage.clickOnConfirmButton();

    }
}

