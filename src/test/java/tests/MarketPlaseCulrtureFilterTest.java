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
    public void exampleTest() {
        welcomePage.skipWelcomeTour();

        autorization("9054733762",1);

        List<String> cultureAssertList = new ArrayList<>();
        while (cultureAssertList.size()<26){
            List<WebElement> visableCulture = marcetplacePage.getVisableCulture();
for (WebElement cultureElem: visableCulture){
    String culture = cultureElem.getText();
    if (!cultureAssertList.contains(culture)){
        cultureAssertList.add(marcetplacePage.chouseCulturefilter(culture));
        assertMCS.equalsTrue(marketFilterPage.getTitle().equals(culture),"название заголовка " +
                marketFilterPage.getTitle() +  " страницы не соответствует выбранному фильтру "+culture);
        assertMCS.equalsTrue(marketFilterPage.getShortMarcetPlaseDeclaration(0).getCultureName().equals(culture),"первая культура " +
                marketFilterPage.getShortMarcetPlaseDeclaration(0).getCultureName()+  " не соответствует выбранному фильтру "  + culture);

    }
marcetplacePage.swipeСulture("right");
}

        }





    }
}

