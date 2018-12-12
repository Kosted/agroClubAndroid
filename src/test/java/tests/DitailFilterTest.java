package tests;

import org.junit.Test;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;


public class DitailFilterTest extends SuperTest {


    public DitailFilterTest() {
        super(web_a);
    }

    @Test
    public void ditailFilterTest() {
        welcomePage.skipWelcomeTour();

        registration(null, null, null);

        // loginPage.assertion();

        //loginPage.chousePurchaser();

        // loginPage.insertPhoneNumber("9054733762");

//        loginPage.clickOnLoginButton();
//
//        smsCodeAcceptPage.assertion();
//        smsCodeAcceptPage.insertCorrectSmsCode();

//        priceUpdatePage.assertion();
//        priceUpdatePage.clickOnConfirmButton();


        marcetplacePage.chouseCulturefilter("Пшеница 5 класс");

        ShortMarcetPlaseDeclaration firstShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);


        marketFilterPage.clickOnFilterButton();

        marketChousFilterPage.setFilter("Протеин(белок), %", "11", "less");
        marketChousFilterPage.clickOnConfirmButton();

        ShortMarcetPlaseDeclaration secondShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);

        if (firstShortMarcetPlaseDeclaration.equals(secondShortMarcetPlaseDeclaration))
            assertMCS.equalsTrue(Integer.parseInt(firstShortMarcetPlaseDeclaration.getMainAttribute())<=11, "была оставлена карточка не соответствующая фильтру.");
        else
            assertMCS.equalsTrue(Integer.parseInt(secondShortMarcetPlaseDeclaration.getMainAttribute())<=11, "была выведена карточка не соответствующая фильтру.");




    }
}

