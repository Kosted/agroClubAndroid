package tests;

import org.junit.Test;
import superClasses.SuperTest;

import java.util.Random;


public class ProfileTest extends SuperTest {


    public ProfileTest() {
        //super(web_a);
    }

    @Test
    public void profileUpdate() {

        String userName = "Пользователь Созданный Для";
        String userEmail = "email@asdf.ru";

        String userPhoneNumber = registration(userName, userEmail, null);

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

        autorization(userPhoneNumber, 1);

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


