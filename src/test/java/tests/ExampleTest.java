package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import subPageObj.CultureLib;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.List;


public class ExampleTest extends SuperTest {


    public ExampleTest() {
        super(web_a);
    }

    @Test
    public void exampleTest() {
        welcomePage.skipWelcomeTour();
        autorization("9054733762", 1);
        priceUpdatePage.clickOnConfirmButton();

       // loginPage.assertion();

        //loginPage.chousePurchaser();

       // loginPage.insertPhoneNumber("9054733762");

//        loginPage.clickOnLoginButton();
//
//        smsCodeAcceptPage.assertion();
//        smsCodeAcceptPage.insertCorrectSmsCode();

//        priceUpdatePage.assertion();
//        priceUpdatePage.clickOnConfirmButton();


        marcetplacePage.chouseCulturefilter("Рожь");

        marketFilterPage.clickOnFilterButton();

        marketChousFilterPage.setFilter("Протеин(белок), %","11" ,"less");
        marketChousFilterPage.clickOnConfirmButton();

 marketFilterPage.clickOnFilterButton();

        marketChousFilterPage.setFilter("Число падения, с","12" ,"more");
        marketChousFilterPage.clickOnConfirmButton();

    //    ShortMarcetPlaseDeclaration shortMarcetPlaseDeclaration = marcetplacePage.getShortMarcetPlaseDeclaration(0);

//        FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration = marcetplacePage.chouseDeclaration(0, false);
//        marcetplacePage.clickOnBackButton();
//        ShortMarcetPlaseDeclaration shortMarcetPlaseDeclaration = marcetplacePage.getShortMarcetPlaseDeclaration(0);




        System.out.println("уря");





    }
}

