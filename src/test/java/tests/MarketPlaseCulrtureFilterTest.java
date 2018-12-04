package tests;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import subPageObj.FullMarcetPlaseDeclaration;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.*;


public class MarketPlaseCulrtureFilterTest extends SuperTest {


    public MarketPlaseCulrtureFilterTest() {
        super(web_a);
    }

    @Test
    public void marketPlaseCulrtureFilterTest() {
        welcomePage.skipWelcomeTour();

        autorization("9054733762", 1);
        priceUpdatePage.clickOnConfirmButton();

        List<String> cultureAssertList = new ArrayList<>();
        while (cultureAssertList.size() < 26) {
            List<WebElement> visableCulture = marcetplacePage.getVisableCulture();

            for (int i=0; i<visableCulture.size(); i++/*WebElement cultureElem : visableCulture*/) {

                visableCulture = marcetplacePage.getVisableCulture();

                String culture = visableCulture.get(i).getText();

                if (!cultureAssertList.contains(culture)) {
                    cultureAssertList.add(marcetplacePage.chouseCulturefilter(culture));
                    assertMCS.equalsTrue(marketFilterPage.getTitle().equals(culture), "название заголовка " +
                            marketFilterPage.getTitle() + " страницы не соответствует выбранному фильтру " + culture);

                    ShortMarcetPlaseDeclaration shortMarcetPlaseDeclaration = marketFilterPage.getShortMarcetPlaseDeclaration(0);
                    if (shortMarcetPlaseDeclaration!=null)
                    assertMCS.equalsTrue(shortMarcetPlaseDeclaration.getCultureName().equals(culture), "первая культура " +
                            shortMarcetPlaseDeclaration.getCultureName() + " не соответствует выбранному фильтру " + culture);
                    marketFilterPage.clickOnBackButton();
                }
            }
            marcetplacePage.swipeСulture("right");

        }


    }
}

