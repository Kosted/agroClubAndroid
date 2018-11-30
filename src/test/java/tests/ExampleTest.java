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

       // loginPage.assertion();

        //loginPage.chousePurchaser();

       // loginPage.insertPhoneNumber("9054733762");

//        loginPage.clickOnLoginButton();
//
//        smsCodeAcceptPage.assertion();
//        smsCodeAcceptPage.insertCorrectSmsCode();

//        priceUpdatePage.assertion();
//        priceUpdatePage.clickOnConfirmButton();


     //   marcetplacePage.chouseCulturefilter("Рожь");

    //    ShortMarcetPlaseDeclaration shortMarcetPlaseDeclaration = marcetplacePage.getShortMarcetPlaseDeclaration(0);

        FullMarcetPlaseDeclaration fullMarcetPlaseDeclaration = marcetplacePage.chouseDeclaration(0, false);
        marcetplacePage.clickOnBackButton();
        ShortMarcetPlaseDeclaration shortMarcetPlaseDeclaration = marcetplacePage.getShortMarcetPlaseDeclaration(0);
        System.out.println("уря");

//        WebElement recyclerView = driver.findElement(By.className("android.support.v7.widget.RecyclerView"));
//
//        List<WebElement> editTextList = recyclerView.findElements(By.className("android.widget.EditText"));
//
//        for (int i = 0; i<editTextList.size(); i++){
////int i = 1;
//            editTextList.get(i).sendKeys("0" + i);
//            try {
//                System.out.println("get attribute Name "+editTextList.get(i).getAttribute("name"));
//            }catch (Exception e){
//                System.out.println("не могу получить атрибут \"name\" "+e.getMessage());
//            }
//
//            try {
//                System.out.println("get text attribute "+editTextList.get(i).getAttribute("text"));
//            }catch (Exception e){
//                System.out.println("не могу получить атрибут \"text\" "+e.getMessage());
//            }
//            try {
//                System.out.println("get text "+editTextList.get(i).getText());
//            }catch (Exception e){
//                System.out.println("не могу получить атрибут \"text\" "+e.getMessage());
//            }
//
//            try {
//                System.out.println("get attribute className "+editTextList.get(i).getAttribute("className"));
//            }catch (Exception e){
//                System.out.println("не могу получить атрибут \"className\" " +e.getMessage());
//            }
//
//            try {
//                System.out.println("get attribute resourceId "+editTextList.get(i).getAttribute("resourceId"));
//            }catch (Exception e){
//                System.out.println("не могу получить атрибут \"resourceId\" "+e.getMessage());
//            }
//
//            try {
//                System.out.println("get attribute contentDescription "+editTextList.get(i).getAttribute("contentDescription"));
//            } catch (Exception e) {
//                System.out.println("не могу получить атрибут \"contentDescription\" "+e.getMessage());
//            }
//            try {
//                System.out.println("getTagName "+editTextList.get(i).getTagName());
//            } catch (Exception e) {
//                System.out.println("не могу получить getTagName " +e.getMessage());
//            }
//
//        }



    }
}

