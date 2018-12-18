package tests;

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

public class DefaultFilterTest extends SuperTest {




    @Test
    public void defaultFilter() {
        welcomePage.skipWelcomeTour();
        if (loginPage.currentPage()) {
            //registration(null, null, null);
            autorization("9054733762", 1);

            priceUpdatePage.clickOnConfirmButton();
        }

        marcetplacePage.chouseCulturefilter("Пшеница 5 класс");

        ShortMarcetPlaseDeclaration lastVisableShortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(2);


        marketFilterPage.clickOnFilterButton();


        marketChousFilterPage.setFilter("Протеин", "55", "more");
        marketChousFilterPage.setFilter("НДС", "Есть", null);
        marketChousFilterPage.clickOnConfirmButton();

        if (marketFilterPage.getDeclarationListSize() == 3)
            Assert.assertFalse("фильтры не работают, тест не возможен",lastVisableShortMarcetPlaseDeclaration.equals(marketFilterPage.getShortMarcetPlaseDeclaration(2)));

        marketFilterPage.clickOnFilterButton();
        marketChousFilterPage.clickOnDefaultFilterButton();
        marketChousFilterPage.clickOnConfirmButton();

        assertMCS.equalsTrue(lastVisableShortMarcetPlaseDeclaration.equals(marketFilterPage.getShortMarcetPlaseDeclaration(2)),"выдача фильтра не вернулась к первоночальному состоянию после его сброса");

        marketFilterPage.clickOnBackButton();


    }
}

