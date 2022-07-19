package com.milan.testcases;

import com.qa.base.BaseClass;
import com.qa.pages.*;
import com.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomePageTest extends BaseClass {
   public LoginPage loginPage;
   public HomePage homePage;
   public TestUtil testUtil;
   public ContactsPage contactsPage;
   public DealsPage dealsPage;
   public TasksPage tasksPage;

    public HomePageTest(){
        // calling BaseClass constructor from HomePgeTest
        super();
    }
    // All the TestCases should be separated / independent
    // it is the best practice to launch browser everytime

    @BeforeMethod
    public void setUp() {
        System.out.println("Launch Browser with Homepage!");
        // calling initialization() from BaseClass
        BaseClass.initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        dealsPage = new DealsPage();
        tasksPage = new TasksPage();
        loginPage = new LoginPage();
        // to access HomePage we
        // have to log in first that's why we are calling login method here
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    // Test Cases
    @Test(priority = 1)
    public void verifyHomePageTitleTest(){
        System.out.println("Verify Home Page Title!");
        String homePageTitle = homePage.verifyHomePageTitle();
        Assert.assertEquals(homePageTitle,"CRMPRO", "HomePageTitle not matched!");
    }

    @Test(priority = 2)
    public void verifyUserNameTest(){
        System.out.println("Verify After Login User Name Showing or Not in Home Page!");
        testUtil.switchToFrame();
        Assert.assertTrue(homePage.verifyRightUserName());
    }
    @Test(priority = 3)
    public void clickOnContactLinksTest() throws InterruptedException {
        System.out.println("Verify Clicks on ContactsLink in Home Page!");
        // calling this because contacts page is in frame that's why
        testUtil.switchToFrame();
        contactsPage = homePage.clickOnContactsLink();
        Thread.sleep(5);
    }

    @Test(priority = 4)
    public void clickOnDealsPageTest() throws InterruptedException {
        System.out.println("Verify Clicking On DealsPage in Home Page!");
        // calling this because contacts page is in frame that's why
        testUtil.switchToFrame();
        dealsPage = homePage.clickOnDealsLink();
        Thread.sleep(5);
    }

    @Test(priority = 5)
    public void clickOnTasksPageTest() throws InterruptedException {
        System.out.println("Verify Clicking on TasksPage in Home Page!");
        // calling this because contacts page is in frame that's why
        testUtil.switchToFrame();
        tasksPage = homePage.clickOnTasksLink();
        Thread.sleep(5);
    }

    @AfterMethod
    public  void tearDown(){
        System.out.println("Close Browser!");
        driver.quit();
    }
}
