package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.MarcetplacePage;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;

import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class MenuAddressTest extends SuperTest {


    public MenuAddressTest(Rols role) {
        this.role = role;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER},
                {PURCHASER}
        });
    }

    private Rols role;

    @Test
    public void addres() {

        String userPhoneNumber = registration(null, null, null, role);

        marcetplacePage.clickOnMenuButton();
        menuPage.clickOnAddressButton();

        Integer addresSize = myAddressPage.getMyAddresListSize();
        assertMCS.equalsFalse(myAddressPage.getMyAddresUpdateIsPossible(0), "единственный адрес не является основным - у него имеется кнопка для добавления его в основной адрес");


        myAddressPage.clickOnAddAddresButton();

        addAddressPage.assertion(role);
        //addAddressPage.clickOnAllowPermissionButton();

        String newAddres = "Добавленный адрес";
        addAddressPage.insertOnAddressNameField(newAddres);
        addAddressPage.clickOnNextButton();




        assertMCS.equalsFalse(addresSize.equals(myAddressPage.getMyAddresListSize()), "адресс не был добавлен в мои адреса");
        assertMCS.equalsTrue(myAddressPage.getMyAddresName(1).equals(newAddres) && myAddressPage.getMyAddresUpdateIsPossible(1), "добавленный адрес не стал дополнительным, нет возможности сделать его основным");

        myAddressPage.setMyAddresMain(1);



        //myAddressPage.swipeUp();

        assertMCS.equalsTrue(myAddressPage.getMyAddresName(0).equals(newAddres + " (Основной)")  , "другой адресс не стал основным");
        assertMCS.equalsFalse(myAddressPage.getMyAddresUpdateIsPossible(0)  , "у другого адресса не исчезла кнопка меню");

        assertMCS.equalsTrue(myAddressPage.getMyAddresName(1).equals("тестовый адрес") && myAddressPage.getMyAddresUpdateIsPossible(1), "тестовый адресс не стал дополнительным");


        myAddressPage.clickOnBackButton();

        menuPage.clickOnLogautButton();


    }

    @Test
    public void marketplaseAddres() {

        registration(null, null, null,role);

        if (role == Rols.FARMER)
            marcetplacePage.chousFarmerMarketplase(MarcetplacePage.MarketSections.HARVEST);

        assertMCS.equalsTrue(marcetplacePage.getMarketAddres()==null, "на рынке изначально был задан адрес приемки");

        marcetplacePage.clickOnchouseAddresButton();

        String addres = myAddressPage.getMyAddresAddres(0);
        myAddressPage.chouseAddres(0);

        assertMCS.equalsTrue(addres.equals(marcetplacePage.getMarketAddres()),"адресс не подтянулся в рынок после нажатия");

        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();

    }
}


