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
// деректива компилятора для использования разных данных для одних и тех же тестов
@RunWith(value = Parameterized.class)
// непосредственно сам класс теста. наследуется от класса супер тест, который реализует открытие приложения и другие штуки общие для всех тестов
public class MenuAddressTest extends SuperTest {

//    конструктор класса который принимает роль которая используется в регистрации
    public MenuAddressTest(Rols role) {
        this.role = role;
    }

    // непосредственно сами параметры, можешь не разбирать
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER},
                {PURCHASER}
        });
    }

    //переменная в которой хранится роль - для регистрации
    private Rols role;

    // метод тест
    @Test
    public void addres() {

        // это мной написанный метод реализующий все этапы регистрации,
        // на врод принимает юзернейм, имейл, номер телефона и роль. если вместо этих значений передать null, то будет использовано значение по умолчанию
        // метод рандомит телефон поэтому метод возвращает его и я записываю его в  переменную userPhoneNumber
        String userPhoneNumber = registration(null, null, null, role);

        // метод который на рынке нажимает на кнопку перехода в меню
        marcetplacePage.clickOnMenuButton();

        // клик на адресс в меню
        menuPage.clickOnAddressButton();

        // метод получает количество адресов у пользователя
        Integer addresSize = myAddressPage.getMyAddresListSize();

        // проверка на то, что
        assertMCS.equalsFalse(myAddressPage.getMyAddresUpdateIsPossible(0), "единственный адрес не является основным - у него имеется кнопка для добавления его в основной адрес");

        // в моих адресах клик на кнопку добавления адреса
        myAddressPage.clickOnAddAddresButton();

        // провекра на то, что в добавлении адреса написано правильно для соответствующей роли: адрес приемки/отгрузки
        addAddressPage.assertion(role);
        //addAddressPage.clickOnAllowPermissionButton();

        // создаем переменную для хранения названия нового адреса
        String newAddres = "Добавленный адрес";

        // в поле названия адреса заносим его
        addAddressPage.insertOnAddressNameField(newAddres);

        // нажимаем на кнопку продолжить
        addAddressPage.clickOnNextButton();



        // проверка на то что адресс был добавлен: размер списка адресов стал больше
        assertMCS.equalsFalse(addresSize.equals(myAddressPage.getMyAddresListSize()), "адресс не был добавлен в мои адреса");

        // проверяем, что у новога адреса присутствует кнопка меню для того что бы сделать его основным, а у основного она отсутствует
        assertMCS.equalsTrue(myAddressPage.getMyAddresName(1).equals(newAddres) && myAddressPage.getMyAddresUpdateIsPossible(1), "добавленный адрес не стал дополнительным, нет возможности сделать его основным");

        // метод делает добавочный адресс основным
        myAddressPage.setMyAddresMain(1);



        //myAddressPage.swipeUp();
        // проверяем что у нового адреса который теперь основной добавилась приписка основной
        assertMCS.equalsTrue(myAddressPage.getMyAddresName(0).equals(newAddres + " (Основной)")  , "другой адресс не стал основным");

        // проверка
        assertMCS.equalsFalse(myAddressPage.getMyAddresUpdateIsPossible(0)  , "у другого адресса не исчезла кнопка меню");

        // проверка
        assertMCS.equalsTrue(myAddressPage.getMyAddresName(1).equals("тестовый адрес") && myAddressPage.getMyAddresUpdateIsPossible(1), "тестовый адресс не стал дополнительным");

// клик на кнопку назад
        myAddressPage.clickOnBackButton();

        // клик на кнопку разлогина
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


