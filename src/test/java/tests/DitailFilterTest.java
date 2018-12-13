package tests;

import helperClasses.WebActions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class DitailFilterTest extends SuperTest {

    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{

                {"Пшеница 5 класс", "Протеин(белок), %", "11", "less"},
                {"Рожь", "Влажность, %", "12", "more"},
                {"Пшеница 5 класс", "НДС", "Есть", null}
        });

    }

    private String culture;
    private String property;
    private String value;
    private String sign;

    public DitailFilterTest(String culture, String property, String value, String sign) {
        this.culture = culture;
        this.property = property;
        this.value = value;
        this.sign = sign;
    }


    @Test
    public void ditailFilterTest() {
        welcomePage.skipWelcomeTour();
        if (loginPage.currentPage())
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

        //ShortMarcetPlaseDeclaration firstShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);


        marketFilterPage.clickOnFilterButton();

        marketChousFilterPage.clickOnDefaultFilterButton();

        marketChousFilterPage.setFilter(property, value, sign);
        marketChousFilterPage.clickOnConfirmButton();


        FullMarcetPlaseDeclaration[] filterResult = new FullMarcetPlaseDeclaration[3];

        FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration;
        for (int i = 0; i < 3; i++) {
            //filterResult[i] = marketFilterPage.chouseDeclaration(0, false);
            fullMarcetPlaseDeclaration = marketFilterPage.chouseDeclaration(0, false);

            if (fullMarcetPlaseDeclaration != null) {

                if (sign != null)
                    assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getAttribute(property) <= Integer.getInteger(value), "была выведена карточка не соответствующая фильтру.");
                else
                    switch (property) {
                        case "Доставка": {
                            if (value.equals("С доставкой"))
                                assertMCS.equalsFalse(fullMarcetPlaseDeclaration.getPriceWithDeliverySeller().equals("-"), "была выведена карточка не соответствующая фильтру.");
                            else
                                assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getPriceWithDeliverySeller().equals("-"), "была выведена карточка не соответствующая фильтру.");

                            break;
                        }
                        case "НДС": {
                            if (value.equals("Есть"))
                                assertMCS.equalsFalse(fullMarcetPlaseDeclaration.getNds() != null, "была выведена карточка не соответствующая фильтру.");
                            else
                                assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getNds() == null, "была выведена карточка не соответствующая фильтру.");

                            break;
                        }
                        case "Условия оплаты": {
                            if (value.equals("Предоплата"))
                                assertMCS.equalsFalse(fullMarcetPlaseDeclaration.getFirstTermsOfPayment().equals("-"), "была выведена карточка не соответствующая фильтру.");
                            else
                                assertMCS.equalsFalse(fullMarcetPlaseDeclaration.getSecondTermsOfPayment().equals("-"), "была выведена карточка не соответствующая фильтру.");

                            break;
                        }
                    }

            }


        }
marketFilterPage.clickOnBackButton();

        //ShortMarcetPlaseDeclaration secondShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);


//        if (firstShortMarcetPlaseDeclaration.equals(secondShortMarcetPlaseDeclaration))
//            assertMCS.equalsTrue(Integer.parseInt(firstShortMarcetPlaseDeclaration.getMainAttribute().replaceAll("[^0-9]+", "")) <= 11, "была оставлена карточка не соответствующая фильтру.");
//        else
//            assertMCS.equalsTrue(Integer.parseInt(secondShortMarcetPlaseDeclaration.getMainAttribute().replaceAll("[^0-9]+", "")) <= 11, "была выведена карточка не соответствующая фильтру.");
//

    }
}

