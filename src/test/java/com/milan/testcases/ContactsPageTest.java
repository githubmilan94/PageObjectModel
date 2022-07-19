package com.milan.testcases;

import com.qa.base.BaseClass;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactsPageTest extends BaseClass {

    public LoginPage loginPage;
    public HomePage homePage;
    public ContactsPage contactsPage;
    public TestUtil testUtil;

    public static String sheetName ="contacts";

      public ContactsPageTest(){
          super();
      }

    @BeforeMethod
    public void setUp() {
        System.out.println("Launch Browser for ContactPage!");
        BaseClass.initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        // after login , switch to frame then click on contacts link, that's how it will work
        testUtil.switchToFrame();
        contactsPage = homePage.clickOnContactsLink();
    }

    @Test(priority = 1)
    public void verifyContactsPageLabel(){
        System.out.println("Verify after clicking on contacts link, contacts page with label displaying or not!");
        // no need to write here
        //testUtil.switchToFrame();
        Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label missing from the Page!");
    }

    @Test(priority = 2)
    public void selectSingleContactsTest() throws InterruptedException {
        System.out.println("Verify checkbox selected or not for single one!");
        contactsPage.selectContactsByName("John Smith");
        Thread.sleep(4000);
    }
    @Test(priority = 3)
    public void selectMultipleContactsTest() throws InterruptedException {
        System.out.println("Verify checkbox selected or not! for multiple");
        contactsPage.selectContactsByName("willium smith");
        contactsPage.selectContactsByName("Jack Sparrow");
        Thread.sleep(4000);
    }

    @DataProvider
    public Object[][] getTestCRMData() throws IOException {
        Object upload_data [][] = TestUtil.getTestData(sheetName);

        return upload_data;
    }
    @Test(priority = 4, dataProvider = "getTestCRMData")
    public  void validateCreateNewContact(String title, String firstName,String lastName, String company) throws Exception {
          //1st will come home page
          // then click on contact page
        // newContactLink already created on home page so use it
        //Thread.sleep(2000);
        // because contact page is a part of home page that's why we are calling this by homepage object
        homePage.clickOnNewContactLink();
        // now fill the form
        // to do this we need contact page object
       // contactsPage.createNewContact("Mr.","TOM","JERRY","GOOGLE");
        Thread.sleep(2000);
        contactsPage.createNewContact(title,firstName,lastName,company);
    }
    @AfterMethod
    public void tearDown(){
        System.out.println("Close Browser for contact page!");
        driver.quit();
    }


}
