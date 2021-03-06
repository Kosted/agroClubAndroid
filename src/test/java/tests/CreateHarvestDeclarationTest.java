package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.CreateDeclarationPropertyPage;
import pageObjects.harvest.HarvestBuyConditionPage;
import pageObjects.MarcetplacePage;
import pageObjects.MyDeclarationPage;
import subPageObj.FullMyDeclaration;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;

import static pageObjects.CreateDeclarationPropertyPage.Sign.LESS;
import static pageObjects.CreateDeclarationPropertyPage.Sign.MORE;
import static pageObjects.harvest.HarvestBuyConditionPage.PaymentDelay.FIVEDAYS;
import static pageObjects.harvest.HarvestBuyConditionPage.PaymentDelay.WITOUTDELAY;
import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class CreateHarvestDeclarationTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER, "Пшеница 3 класс", "15", "11", "Протеин", "10", LESS, false, true, WITOUTDELAY},
                {FARMER, "Рожь", "9", "8", "Влажность", "4", MORE, true, false, FIVEDAYS},
                {PURCHASER, "Пшеница 3 класс", "15", "11", "Протеин", "10", LESS, false, true, WITOUTDELAY},
                {PURCHASER, "Рожь", "9", "8", "Влажность", "4", MORE, true, false, FIVEDAYS}
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

    public CreateHarvestDeclarationTest(Rols role, String culture, String price, String volume, String property, String propertyValue, CreateDeclarationPropertyPage.Sign propertySign, Boolean logistic, Boolean prepayment, HarvestBuyConditionPage.PaymentDelay paymentDelay) {
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
    public void createHarvestDeclaration() {
        registration(null, null, null, role);

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

        createNewDeclarationPage.switchLogistic(logistic);


        createNewDeclarationPage.setField("Условия оплаты", null);

        harvestBuyConditionPage.switchPrepayment(prepayment);

        harvestBuyConditionPage.chousePaymentDelay(paymentDelay);

        harvestBuyConditionPage.clickOnConfirmButton();

        createNewDeclarationPage.clickOnConfirmButton();

        marcetplacePage.clickOnBackButton();

        marcetplacePage.clickOnMyRequestButton();

//////////////

        assertMCS.equalsTrue(myDeclarationPage.currentPage(), "не был осуществелн переход на страницу с моими заявками");

        if (role.equals(FARMER))
            myDeclarationPage.chousFarmerMarketplase(MyDeclarationPage.MarketSections.HARVEST);

        FullMyDeclaration fullMyDeclaration = myDeclarationPage.chouseDeclaration(0, MyDeclarationPage.Action.GETDECLARATION);


        assertMCS.setStandartErrorMessege("в созданной заявке ");

        assertMCS.equalsTrue(fullMyDeclaration.getCultureName().contains(culture), "культура создалась не правильно");

        assertMCS.equalsTrue(fullMyDeclaration.getPriceWithoutNds().contains(price), "цена создалась не правильно");

        assertMCS.equalsTrue(fullMyDeclaration.getVolume().contains(volume), "объем создался не правильно");

        // не проверяю корректность создания свойств, ну очень геморойно \\ хотя сейчас нашел у себя функцию для этого, может потом

        assertMCS.equalsTrue(fullMyDeclaration.getLogistic().equals(logistic),
                "логистика создалась не правильно");

        assertMCS.equalsTrue(fullMyDeclaration.getPrepayment().equals(prepayment), "предоплата создалась не правильно");

        String prepaymentDayDelay = fullMyDeclaration.getPaymentDelay().replaceAll("[^0-9]+", "");
        switch (paymentDelay){
            case WITOUTDELAY:
                assertMCS.equalsTrue(prepaymentDayDelay.equals(""), "отсрочка 0 создалась не правильно");
                break;
            case FIVEDAYS:
                assertMCS.equalsTrue(prepaymentDayDelay.equals("5"), "отсрочка 5 создалась не правильно");
                break;
            case THIRTYDAYS:
                assertMCS.equalsTrue(prepaymentDayDelay.equals("30"), "отсрочка 30 создалась не правильно");
                break;
        }

        marcetplacePage.clickOnBackButton();

        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();
    }
}

