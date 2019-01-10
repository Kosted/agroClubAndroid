package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.CreateDeclarationPropertyPage;
import pageObjects.HarvestBuyConditionPage;
import pageObjects.MarcetplacePage;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;


import static pageObjects.CreateDeclarationPropertyPage.Sign.MORE;
import static pageObjects.HarvestBuyConditionPage.PaymentDelay.WITOUTDELAY;
import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class ExampleTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER, "Пшеница 3 класс", "15", "11", "Протеин", "10", MORE, true, true, WITOUTDELAY},
                {PURCHASER}
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
        registration(null, null, null, role);
        //priceUpdatePage.clickOnConfirmButton();

       marcetplacePage.clickOnCreateButton();

       createNewDeclarationPage.farmerChousDeclarationType(MarcetplacePage.MarketSections.HARVEST);

       createNewDeclarationPage.setField("Выберите культуру",null);


        chousListPage.clickOnPropertyField("4 класс");

        createNewDeclarationPage.setField("Цена","15");
        createNewDeclarationPage.setField("Объем","16");

        createNewDeclarationPage.setField("показатели",null);

        createDeclarationPropertyPage.setProperty("Протеин", "11", MORE);

        createDeclarationPropertyPage.clickOnConfirmButton();

        createNewDeclarationPage.switchLogistic(true);
        createNewDeclarationPage.switchLogistic(false);


        createNewDeclarationPage.setField("Условия оплаты", null);

        harvestBuyConditionPage.switchPrepayment(false);
        harvestBuyConditionPage.switchPrepayment(true);
        harvestBuyConditionPage.switchPrepayment(false);
        harvestBuyConditionPage.switchPrepayment(true);


        harvestBuyConditionPage.chousePaymentDelay(WITOUTDELAY);


        harvestBuyConditionPage.clickOnConfirmButton();

        createNewDeclarationPage.clickOnConfirmButton();

        System.out.println("уря");





    }
}

