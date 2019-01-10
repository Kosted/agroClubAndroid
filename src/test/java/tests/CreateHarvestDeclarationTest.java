package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.CreateDeclarationPropertyPage;
import pageObjects.HarvestBuyConditionPage;
import pageObjects.MarcetplacePage;
import subPageObj.FullMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;

import static pageObjects.CreateDeclarationPropertyPage.Sign.LESS;
import static pageObjects.CreateDeclarationPropertyPage.Sign.MORE;
import static pageObjects.HarvestBuyConditionPage.PaymentDelay.FIVEDAYS;
import static pageObjects.HarvestBuyConditionPage.PaymentDelay.WITOUTDELAY;
import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class CreateHarvestDeclarationTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER, "Пшеница 3 класс", "15", "11", "Протеин", "10", LESS, false, true, WITOUTDELAY}/*,
                {FARMER, "Рожь", "9", "8", "Влажность", "4", MORE, true, false, FIVEDAYS}*/
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

        FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration = myDeclarationPage.chouseDeclaration(0);


        assertMCS.setStandartErrorMessege("в созданной заявке ");

        assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getCultureName().contains(culture), "культура создалась не правильно");

        assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getMyPriceWithoutLogistic().contains(price), "цена создалась не правильно");

        assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getVolume().contains(volume), "объем создался не правильно");

        //assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getVolume().contains(volume), "объем создался не правильно");

        // не проверяю корректность создания свойств, ну очень геморойно \\ хотя сейчас нашел у себя функцию для этого, может потом

        assertMCS.equalsTrue((!fullMarcetPlaseDeclaration.getMyPriceWithLogistic().equals("-") && logistic== true) ||
                        (fullMarcetPlaseDeclaration.getMyPriceWithLogistic().equals("-") && logistic== false),
                "логистика создалась не правильно");

        assertMCS.equalsTrue((!fullMarcetPlaseDeclaration.getFirstTermsOfPayment().equals("-") && prepayment== true) ||
                (fullMarcetPlaseDeclaration.getFirstTermsOfPayment().equals("-") && prepayment== false), "предоплата создалась не правильно");

        String prepaymentDayDelay = fullMarcetPlaseDeclaration.getSecondTermsOfPayment().replaceAll("[^0-9]+", "");
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

