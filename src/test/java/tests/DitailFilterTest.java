package tests;

import helperClasses.WebActions;
import org.junit.Assert;
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

                {"Пшеница 5 класс", "Протеин", "11", "less"},
                {"Пшеница 5 класс", "НДС", "Есть", null},
                {"Рожь", "Влажность", "12", "more"}
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
        if (loginPage.currentPage()) {
            //registration(null, null, null);
            autorization("9054733762", 1);

            priceUpdatePage.clickOnConfirmButton();
        }

        marcetplacePage.chouseCulturefilter(culture);

        marketFilterPage.clickOnFilterButton();

        marketChousFilterPage.clickOnDefaultFilterButton();

        marketChousFilterPage.setFilter(property, value, sign);
        marketChousFilterPage.clickOnConfirmButton();


        FullMarcetPlaseDeclaration[] filterResult = new FullMarcetPlaseDeclaration[3];

        assertMCS.setStandartErrorMessege(" " + culture + ", " + property + ", " + value + ", " + sign + " ");

        FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration;
        for (int i = 0; i < 3; i++) {
            //filterResult[i] = marketFilterPage.chouseDeclaration(0, false);
            fullMarcetPlaseDeclaration = marketFilterPage.chouseDeclaration(0, false);

            if (fullMarcetPlaseDeclaration != null) {

                if (sign != null)
                    assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getAttribute(property) <= Integer.parseInt(value), "была выведена карточка не соответствующая фильтру.");
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
                                assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getNds() != null, "была выведена карточка не соответствующая фильтру.");
                            else
                                assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getNds() == null, "была выведена карточка не соответствующая фильтру.");

                            break;
                        }
                        case "Условия оплаты": {
                            if (value.equals("Предоплата"))
                                assertMCS.equalsTrue(fullMarcetPlaseDeclaration.getFirstTermsOfPayment().equals("-"), "была выведена карточка не соответствующая фильтру.");
                            else
                                assertMCS.equalsFalse(fullMarcetPlaseDeclaration.getSecondTermsOfPayment().equals("-"), "была выведена карточка не соответствующая фильтру.");

                            break;
                        }
                    }

            }


        }
        marketFilterPage.clickOnBackButton();

    }


}

