package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.CreateDeclarationPropertyPage;
import pageObjects.HarvestBuyConditionPage;
import pageObjects.MarcetplacePage;
import pageObjects.MarketChousFilterPage;
import subPageObj.CultureLib;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class ExampleTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER},
                {PURCHASER}
        });
    }
    private Rols role;

    public ExampleTest(Rols role) {
        this.role = role;
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

        createDeclarationPropertyPage.setProperty("Протеин", "11", CreateDeclarationPropertyPage.Sign.MORE);

        createDeclarationPropertyPage.setProperty("Сорт", "Твердая", null);

        createDeclarationPropertyPage.clickOnConfirmButton();

        createNewDeclarationPage.switchLogistic(true);
        createNewDeclarationPage.switchLogistic(false);
        createNewDeclarationPage.switchLogistic(true);
        createNewDeclarationPage.switchLogistic(false);

        createNewDeclarationPage.setField("Условия оплаты", null);

        harvestBuyConditionPage.switchPrepayment(false);
        harvestBuyConditionPage.switchPrepayment(true);
        harvestBuyConditionPage.switchPrepayment(false);
        harvestBuyConditionPage.switchPrepayment(true);

        harvestBuyConditionPage.chousePaymentDelay(HarvestBuyConditionPage.PaymentDelay.WITOUTDELAY);
        harvestBuyConditionPage.chousePaymentDelay(HarvestBuyConditionPage.PaymentDelay.FIVEDAYS);
        harvestBuyConditionPage.chousePaymentDelay(HarvestBuyConditionPage.PaymentDelay.THIRTYDAYS);

        harvestBuyConditionPage.clickOnConfirmButton();


        System.out.println("уря");





    }
}

