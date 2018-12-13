package tests;

import helperClasses.WebActions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class DitailFilterTest extends SuperTest {

    @Parameters
    public static Collection<Object[]> data() {

        Object[][] data = new Object[][]{

                {"Пшеница 5 класс", "Протеин(белок), %", "11", "less"},
                {"Рожь", "Влажность, %", "12", "more"}
        };

        return Arrays.asList(data);

    }

    String culture;
    String mainProperty;
    String value;
    String sign;

    public DitailFilterTest(WebActions web_a, String culture, String mainProperty, String value, String sign) {
        super(web_a);
        this.culture = culture;
        this.mainProperty = mainProperty;
        this.value = value;
        this.sign = sign;
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


        marcetplacePage.chouseCulturefilter(culture);

        ShortMarcetPlaseDeclaration firstShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);


        marketFilterPage.clickOnFilterButton();

        marketChousFilterPage.setFilter(mainProperty, value, sign);
        marketChousFilterPage.clickOnConfirmButton();


        ShortMarcetPlaseDeclaration secondShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);

        if (firstShortMarcetPlaseDeclaration.equals(secondShortMarcetPlaseDeclaration))
            assertMCS.equalsTrue(Integer.parseInt(firstShortMarcetPlaseDeclaration.getMainAttribute().replaceAll("[^0-9]+", "")) <= 11, "была оставлена карточка не соответствующая фильтру.");
        else
            assertMCS.equalsTrue(Integer.parseInt(secondShortMarcetPlaseDeclaration.getMainAttribute().replaceAll("[^0-9]+", "")) <= 11, "была выведена карточка не соответствующая фильтру.");


    }
}

