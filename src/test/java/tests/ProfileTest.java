package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import superClasses.SuperTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static superClasses.SuperTest.Rols.FARMER;
import static superClasses.SuperTest.Rols.PURCHASER;

@RunWith(value = Parameterized.class)
public class ProfileTest extends SuperTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {FARMER},
                {PURCHASER}
        });
    }


    private Rols role;

    public ProfileTest() {
        //super(web_a);
        this.role = role;
    }

    @Test
    public void profileUpdate() {

        String userName = "Пользователь Созданный Для";
        String userEmail = "email@asdf.ru";

        String userPhoneNumber = registration(userName, userEmail, null, role);

        marcetplacePage.clickOnMenuButton();
        menuPage.clickOnProfileButton();

        profilePage.assertion();

        profilePage.insertFullName("");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "было принято пустое ФИО");

        profilePage.insertFullName("Пользователь Созданный");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "было принято неверное ФИО: нет отчества");

        profilePage.insertFullName("Пользователь Созданный sfg");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "было принято неверное ФИО: с латинницей");

        profilePage.insertFullName("Пользователь Созданный Счетвертым Словом");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "было принято неверное ФИО: 4 слово");

        profilePage.insertFullName("Пользователь Созданный Счисл1ами1");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "было принято неверное ФИО: числа в отчестве");

        profilePage.insertFullName("Пользователь Созданный Соспецсимв!олами");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "было принято неверное ФИО: спечсимволы");

        String newName = "Пользователь Созданный Автотестом";
        String newEmail = "test@mail.com";

        profilePage.insertFullName(newName);

        profilePage.insertEmail("test@mail.");
        assertMCS.equalsFalse(profilePage.saveButtonIsEnabled(), "был принят не валидный имейл");

        profilePage.insertEmail(newEmail);
        profilePage.clickOnSaveButton();

        profilePage.clickOnBackButton();

        menuPage.clickOnLogautButton();

        autorization(userPhoneNumber, role);

        marcetplacePage.clickOnMenuButton();

        menuPage.clickOnProfileButton();

        assertMCS.equalsTrue(profilePage.getFullName().equals(newName), "изменения ФИО в профиле не сохранились");
        assertMCS.equalsTrue(profilePage.getEmail().equals(newEmail), "изменения имейла в профиле не сохранились");

        profilePage.clickOnOrganizationButton();

        organizationPage.fullOrganizationInfo();

        organizationPage.clickOnBackButton();

        assertMCS.equalsTrue(profilePage.getOrganizationInfo().equals(organizationPage.getOrganizationName()), "названия организации в краткой карточке и в полной не совпадают");

        assertMCS.equalsFalse(organizationPage.getGeneralDirector().equals(""), "названия организации в краткой карточке и в полной не совпадают");
        assertMCS.equalsFalse(organizationPage.getInn().equals(""), "названия организации в краткой карточке и в полной не совпадают");
        assertMCS.equalsFalse(organizationPage.getKpp().equals(""), "названия организации в краткой карточке и в полной не совпадают");
        assertMCS.equalsFalse(organizationPage.getMainAdress().equals(""), "названия организации в краткой карточке и в полной не совпадают");
        assertMCS.equalsFalse(organizationPage.getNds().equals(""), "названия организации в краткой карточке и в полной не совпадают");
        assertMCS.equalsFalse(organizationPage.getOgrn().equals(""), "названия организации в краткой карточке и в полной не совпадают");
        assertMCS.equalsFalse(organizationPage.getOrganizationName().equals(""), "названия организации в краткой карточке и в полной не совпадают");




        profilePage.clickOnBackButton();


        menuPage.clickOnLogautButton();


    }


}


