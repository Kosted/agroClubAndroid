package tests;

import helperClasses.Swipe;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import subPageObj.NewsCard;
import subPageObj.ShortMarcetPlaseDeclaration;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;

import static superClasses.SuperTest.Rols.*;

@RunWith(value = Parameterized.class)
public class NewsTest extends SuperTest {

    private final Swipe swipedeclarationsDown;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER, "9083333333"},
                {PURCHASER, "9054733762"}
        });
    }

    private Rols role;
    private String phoneNumber;


    public NewsTest(Rols role, String phoneNumber) {
        this.role = role;
        this.phoneNumber = phoneNumber;
        swipedeclarationsDown = new Swipe(web_a, 50, 73, 50, 45, 300);

    }

    @Test
    public void dateNews() {
        welcomePage.skipWelcomeTour();
        if (loginPage.currentPage()) {
            //registration(null, null, null);
            autorization(phoneNumber, role);

            priceUpdatePage.clickOnConfirmButton();

        }

        marcetplacePage.clickOnMenuButton();
        menuPage.clickOnNewsButton();

        NewsCard newsCard0 = newsPage.chouseNews(0);
        newsPage.clickOnBackButton();

        web_a.swipeAction(swipedeclarationsDown);

        NewsCard newsCard1 = newsPage.chouseNews(1);
        newsPage.clickOnBackButton();

        web_a.swipeAction(swipedeclarationsDown);
        NewsCard newsCard2 = newsPage.chouseNews(1);

        newsPage.clickOnBackButton();
        newsPage.clickOnBackButton();

        menuPage.clickOnLogautButton();

        assertMCS.equalsTrue(newsPage.dateCompare(newsCard0.getDate(), newsCard1.getDate()), "первая и вторая новость расположены не в хронологическом порядке");
        assertMCS.equalsTrue(newsPage.dateCompare(newsCard1.getDate(), newsCard2.getDate()), "вторая и третья новость расположены не в хронологическом порядке");


    }

    @Test
    public void detailNews() {
        welcomePage.skipWelcomeTour();
        if (loginPage.currentPage()) {
            //registration(null, null, null);
            autorization(phoneNumber, role);

            priceUpdatePage.clickOnConfirmButton();
            marcetplacePage.clickOnMenuButton();

        }

        menuPage.clickOnNewsButton();

        NewsCard newsCard0 = newsPage.chouseNews(0);

        newsPage.clickOnBackButton();
        newsPage.clickOnBackButton();


        assertMCS.equalsTrue(newsCard0.getDate().equals(newsCard0.getDetailDate()), "дата новости в краткой и детальной карточке не совпадает");

        assertMCS.equalsTrue(newsCard0.getTitle().equals(newsCard0.getDetailTitle()), "название новости в краткой и детальной карточке не совпадает");

        assertMCS.equalsTrue(newsCard0.getText() != null, "отсутствует поле с текстом");

        assertMCS.equalsFalse(newsCard0.getText().equals(""), "поле с текстом пусто");

        assertMCS.equalsTrue(newsCard0.getSource() != null, "отсутствует поле с ссылкой на источник");

        menuPage.clickOnLogautButton();


    }
}

