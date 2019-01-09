package helperClasses;

import pageObjects.*;
import subPageObj.CultureLib;
import superClasses.SuperTest;

import java.util.Random;


public class PageObjects {

    protected static CultureLib cultureLib;

    protected static LoginPage loginPage;
    protected static RegistrationPage registrationPage;
    protected static SmsCodeAcceptPage smsCodeAcceptPage;
    protected static AddAddressPage addAddressPage;
    protected static ChouseOrganizationPage chouseOrganizationPage;
    protected static LogisticPage logisticPage;
    protected static PriceUpdatePage priceUpdatePage;
    protected static MarcetplacePage marcetplacePage;
    protected static WelcomePage welcomePage;
    protected static MenuPage menuPage;
    protected static UserDataAnalisPage userDataAnalisPage;
    protected static AddresManualAddPage addresManualAddPage;
    protected static MyAddressPage myAddressPage;
    protected static ProfilePage profilePage;
    protected static MarketFilterPage marketFilterPage;
    protected static MarketChousFilterPage marketChousFilterPage;
    protected static NewsPage newsPage;
    protected static OrganizationPage organizationPage;
    protected static HarvestBuyConditionPage harvestBuyConditionPage;
    protected static ChousListPage chousListPage;
    protected static CreateNewDeclarationPage createNewDeclarationPage;
    protected static CreateDeclarationPropertyPage createDeclarationPropertyPage;


    private WebActions web_a;

    public PageObjects(WebActions web_a) {
        this.web_a = web_a;
        loginPage = new LoginPage(web_a);
        registrationPage = new RegistrationPage(web_a);
        smsCodeAcceptPage = new SmsCodeAcceptPage(web_a);
        addAddressPage = new AddAddressPage(web_a);
        chouseOrganizationPage = new ChouseOrganizationPage(web_a);
        logisticPage = new LogisticPage(web_a);
        priceUpdatePage = new PriceUpdatePage(web_a);
        marcetplacePage = new MarcetplacePage(web_a);
        welcomePage = new WelcomePage(web_a);
        menuPage = new MenuPage(web_a);
        userDataAnalisPage = new UserDataAnalisPage(web_a);
        addresManualAddPage = new AddresManualAddPage(web_a);
        profilePage = new ProfilePage(web_a);
        myAddressPage = new MyAddressPage(web_a);
        marketFilterPage = new MarketFilterPage(web_a);
        marketChousFilterPage = new MarketChousFilterPage(web_a);
        newsPage = new NewsPage(web_a);
        organizationPage = new OrganizationPage(web_a);
        harvestBuyConditionPage = new HarvestBuyConditionPage(web_a);
        chousListPage = new ChousListPage(web_a);
        createNewDeclarationPage = new CreateNewDeclarationPage(web_a);
        createDeclarationPropertyPage = new CreateDeclarationPropertyPage(web_a);

    }

    public String registration(String userName, String email, String phoneNumber, SuperTest.Rols role) {

        welcomePage.skipWelcomeTour();

        if (priceUpdatePage.currentPage())
            priceUpdatePage.clickOnConfirmButton();
        if (marcetplacePage.currentPage()) {
            marcetplacePage.clickOnMenuButton();
            menuPage.clickOnLogautButton();
        }


        loginPage.assertion();

        if (role == SuperTest.Rols.FARMER)
            loginPage.chouseFarmer();

        else if (role == SuperTest.Rols.PURCHASER) {
            loginPage.chousePurchaser();
        }
        //else  loginPage.chouseDistributor();

        String registrationPhoneNumber = phoneNumber;

        if (registrationPhoneNumber == null)
            registrationPhoneNumber = "904" + new Random().nextInt(999999999);

        loginPage.insertPhoneNumber(registrationPhoneNumber);

        loginPage.clickOnLoginButton();

        smsCodeAcceptPage.assertion();
        smsCodeAcceptPage.insertCorrectSmsCode();

        registrationPage.assertion();

        if (userName == null)
            registrationPage.insertFullName("Пользователь Созданный Автотестом");
        else
            registrationPage.insertFullName(userName);

        if (email != null)
            registrationPage.insertEmail(email);


        registrationPage.clickOnSaveButton();

        chouseOrganizationPage.assertionOrganization();
        chouseOrganizationPage.insertOrganization("А");
        chouseOrganizationPage.chouseOrganization(0);
        chouseOrganizationPage.assertionAcceptOrg();
        chouseOrganizationPage.clickOnSaveButton();

        chouseOrganizationPage.insertSubdivision("1" + new Random().nextInt(9999999));
        chouseOrganizationPage.clickOnSaveButton();

        addAddressPage.clickOnAllowPermissionButton();

        addAddressPage.assertion(role);
        addAddressPage.insertOnAddressNameField("тестовый адрес");
        addAddressPage.clickOnNextButton();

        logisticPage.assertion();
        logisticPage.clickOnAcceptButton();

        return registrationPhoneNumber;
    }

    public void autorization(String phoneNumber, SuperTest.Rols role) {

        loginPage.insertPhoneNumber(phoneNumber);

        if (role == SuperTest.Rols.FARMER)
            loginPage.chouseFarmer();

        else if (role == SuperTest.Rols.PURCHASER) {
            loginPage.chousePurchaser();
        }
        //else  loginPage.chouseDistributor();


        loginPage.clickOnLoginButton();

        smsCodeAcceptPage.assertion();
        smsCodeAcceptPage.insertCorrectSmsCode();

        // priceUpdatePage.clickOnConfirmButton();
    }

}
