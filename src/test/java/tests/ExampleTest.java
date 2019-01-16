package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.CreateDeclarationPropertyPage;
import pageObjects.harvest.HarvestBuyConditionPage;
import pageObjects.MarcetplacePage;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;


import static pageObjects.CreateDeclarationPropertyPage.Sign.MORE;
import static pageObjects.harvest.HarvestBuyConditionPage.PaymentDelay.WITOUTDELAY;
import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class ExampleTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER, "Лен", "10", "10", "Масленистость", "10", MORE, true, true, WITOUTDELAY},
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

        // создание заявки на продажу фермера
        autorization("9050000001",  role);
        //priceUpdatePage.clickOnConfirmButton();

        marcetplacePage.clickOnCreateButton();

        if (role.equals(FARMER))
            createNewDeclarationPage.farmerChousDeclarationType(MarcetplacePage.MarketSections.HARVEST);

        createNewDeclarationPage.setField("Выберите культуру",null);

        chousListPage.clickOnPropertyField(culture);

        createNewDeclarationPage.setField("Цена",price);
        createNewDeclarationPage.setField("Объем",volume);

        createNewDeclarationPage.setField("показатели",null);

        createDeclarationPropertyPage.setProperty(property, propertyValue, propertySign);

        createDeclarationPropertyPage.clickOnConfirmButton();

        createNewDeclarationPage.setField("Условия оплаты", null);

        harvestBuyConditionPage.clickOnConfirmButton();

        createNewDeclarationPage.clickOnConfirmButton();

        marcetplacePage.clickOnBackButton();

        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();

// создание заявки закупщика и создание отклика на заявку фермера


        // создание заявки
        role = PURCHASER;

        autorization("9050000002",  role);

        marcetplacePage.clickOnCreateButton();

        if (role.equals(FARMER))
            createNewDeclarationPage.farmerChousDeclarationType(MarcetplacePage.MarketSections.HARVEST);

        createNewDeclarationPage.setField("Выберите культуру",null);

        chousListPage.clickOnPropertyField(culture);

        createNewDeclarationPage.setField("Цена",price+0);
        createNewDeclarationPage.setField("Объем",volume+0);

        createNewDeclarationPage.setField("показатели",null);

        createDeclarationPropertyPage.setProperty(property, propertyValue, propertySign);

        createDeclarationPropertyPage.clickOnConfirmButton();

        createNewDeclarationPage.setField("Условия оплаты", null);

        harvestBuyConditionPage.clickOnConfirmButton();

        createNewDeclarationPage.clickOnConfirmButton();

        marcetplacePage.clickOnBackButton();

        // отклик

        marcetplacePage.clickOnMarketButton();

        marcetplacePage.chouseCulturefilter("Лен");

        marketFilterPage.chouseDeclaration(0, true);

        marcetplacePage.
    }
}

