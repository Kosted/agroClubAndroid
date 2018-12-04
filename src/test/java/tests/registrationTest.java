package tests;

import org.junit.Test;
import superClasses.SuperTest;

import java.util.Random;


public class registrationTest extends SuperTest {


    public registrationTest() {
        super(web_a);
    }

    @Test
    public void invalidRegistration() {

        welcomePage.skipWelcomeTour();

        loginPage.assertion();

        loginPage.chousePurchaser();

//        loginPage.clickOnPersonalAccept();
//        assertMCS.equalsFalse(userDataAnalisPage.isDocumentEmpty(), " пустое лиц соглашение");
//        userDataAnalisPage.clickOnBackButton();

        loginPage.insertPhoneNumber("904");

        assertMCS.equalsFalse(loginPage.loginButtonIsEnabled(), " кнопка авторизоваться активна при неполном вводе номера телефона");

        loginPage.insertPhoneNumber("фываasdf    ");

        assertMCS.equalsFalse(loginPage.loginButtonIsEnabled(), " кнопка авторизоваться активна при невалидном телефоне");

        String registrationPhoneNumber = "904" + new Random().nextInt(999999999);

        loginPage.insertPhoneNumber(registrationPhoneNumber);

        loginPage.clickOnLoginButton();

        smsCodeAcceptPage.assertion();

        smsCodeAcceptPage.insertSmsCode("qwertyйцукен");

        assertMCS.equalsFalse(web_a.waitStalenessOf(smsCodeAcceptPage.getSmsCodeField()), "был принят смс код из букв qwertyйцукен");

        smsCodeAcceptPage.insertSmsCode("12345");
        assertMCS.equalsFalse(web_a.waitStalenessOf(smsCodeAcceptPage.getSmsCodeField()), "был принят неполный смс код 12345 ");

        String smsCode = smsCodeAcceptPage.getSmsCode();
        smsCodeAcceptPage.absolutResendCode();
        assertMCS.equalsFalse(smsCodeAcceptPage.getSmsCodeField().equals(smsCode), "не был выслан новый смс код " + smsCode);
//
//
//        smsCodeAcceptPage.insertSmsCode("123456");
//        assertMCS.equalsTrue(web_a.isPresent(smsCodeAcceptPage.getSmsCodeField())!=null,"был принят неверный смс код 123456 при верном "+ smsCode);

        smsCodeAcceptPage.insertCorrectSmsCode();
        assertMCS.equalsFalse(web_a.waitStalenessOf(smsCodeAcceptPage.getSmsCodeField()), "не был принят новый смс код");


        registrationPage.assertion();
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "было принято пустое ФИО");

        registrationPage.insertFullName("Пользователь Созданный");
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "было принято неверное ФИО: нет отчества");

        registrationPage.insertFullName("Пользователь Созданный sfg");
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "было принято неверное ФИО: с латинницей");

        registrationPage.insertFullName("Пользователь Созданный Счетвертым Словом");
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "было принято неверное ФИО: 4 слово");

        registrationPage.insertFullName("Пользователь Созданный Счисл1ами1");
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "было принято неверное ФИО: числа в отчестве");

        registrationPage.insertFullName("Пользователь Созданный Соспецсимв!олами");
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "было принято неверное ФИО: спечсимволы");

        registrationPage.insertFullName("Пользователь Созданный Автотестом");

        registrationPage.insertEmail("test@mail.");
        assertMCS.equalsFalse(registrationPage.saveButtonIsEnabled(), "был принят не валидный имейл");

        registrationPage.insertEmail("test@mail.com");
        registrationPage.clickOnSaveButton();
/////
        chouseOrganizationPage.assertionOrganization();

        chouseOrganizationPage.insertOrganization("А");

        chouseOrganizationPage.chouseOrganization(0);

        chouseOrganizationPage.assertionAcceptOrg();

        chouseOrganizationPage.clickOnSaveButton();

        chouseOrganizationPage.insertSubdivision("а");
        chouseOrganizationPage.clickOnSaveButton();

        chouseOrganizationPage.assertionAcceptOrg();

        chouseOrganizationPage.insertSubdivision("1" + new Random().nextInt(9999999));
        chouseOrganizationPage.clickOnSaveButton();

        addAddressPage.clickOnDenyPermissionButton();

        addAddressPage.assertion();
        addAddressPage.insertOnAddressNameField("тестовый адресс");
        addAddressPage.clickOnNextButton();

        addAddressPage.assertion();
        addAddressPage.clickOnManualEditAddresButton();

        addresManualAddPage.assertion();
        addresManualAddPage.clickOnBackButton();

        addAddressPage.clickOnAllowPermissionButton();
        addAddressPage.clickOnNextButton();

        logisticPage.assertion();
        logisticPage.clickOnAcceptButton();

//        priceUpdatePage.assertion();
//        priceUpdatePage.clickOnConfirmButton();


//временное решение пока не добавлена полноценная сраница рынка
        assertMCS.equalsTrue(marcetplacePage.currentPage(), "не был осуществлен переход на страницу рынка");

        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();

        loginPage.assertion();

        loginPage.insertPhoneNumber(registrationPhoneNumber);
        loginPage.chouseFarmer();
        loginPage.clickOnLoginButton();

    }

      @Test
    public void correctRegistration() {

        welcomePage.skipWelcomeTour();

        if (priceUpdatePage.currentPage())
            priceUpdatePage.clickOnConfirmButton();
        if (marcetplacePage.currentPage()) {
            marcetplacePage.clickOnMenuButton();
            menuPage.clickOnLogautButton();
        }


        loginPage.assertion();

        loginPage.chousePurchaser();

        String registrationPhoneNumber = "904" + new Random().nextInt(999999999);

        loginPage.insertPhoneNumber(registrationPhoneNumber);

        loginPage.clickOnLoginButton();

        smsCodeAcceptPage.assertion();
        smsCodeAcceptPage.insertCorrectSmsCode();

        registrationPage.assertion();
        registrationPage.insertFullName("Пользователь-Дифис Созданный Автотестём");

        registrationPage.clickOnSaveButton();

        chouseOrganizationPage.assertionOrganization();
        chouseOrganizationPage.insertOrganization("А");
        chouseOrganizationPage.chouseOrganization(0);
        chouseOrganizationPage.assertionAcceptOrg();
        chouseOrganizationPage.clickOnSaveButton();

        chouseOrganizationPage.insertSubdivision("1" + new Random().nextInt(9999999));
        chouseOrganizationPage.clickOnSaveButton();

        addAddressPage.clickOnAllowPermissionButton();

        addAddressPage.assertion();
        addAddressPage.insertOnAddressNameField("12№тестовый адресс145/");
        addAddressPage.clickOnNextButton();

        logisticPage.assertion();
        logisticPage.clickOnAcceptButton();

//        priceUpdatePage.assertion();
//        priceUpdatePage.clickOnConfirmButton();

//временное решение пока не добавлена полноценная сраница рынка
        assertMCS.equalsTrue(marcetplacePage.currentPage(), "не был осуществлен переход на страницу рынка");
//
        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();

        loginPage.assertion();

        autorization(registrationPhoneNumber, 1);

        //временное решение пока не добавлена полноценная сраница рынка
        assertMCS.equalsTrue(marcetplacePage.currentPage(), "не был осуществлен переход на страницу рынка");
//
        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnLogautButton();

        loginPage.assertion();

    }

}


