package com.milan.testcases;

import com.qa.base.BaseClass;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {
    public LoginPage loginPage;
    public HomePage homePage;

    public LoginPageTest() {
        // compulsory to call BaseClass Constructor that is BaseClass, super() is doing that
        super();
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("Launch Browser for Login Page!");
        // call initialize so that all the initialization of properties will be here
        BaseClass.initialization();
        // Create LoginPage clas object to access all the loginPage class properties
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginPageTitleTest(){
        System.out.println("Verify LoginPage Title!");
        // because validateLogin() coming from LoginPage class that;s why we are calling loginPage object
        // I can access all the methods of loginPage class
        String title =  loginPage.validateLoginPageTitle();
        Assert.assertEquals(title,"Free CRM - CRM software for customer relationship management, sales, and support.");
    }

    @Test(priority = 2)
    public void validateCRMLogoImageTest(){
        System.out.println("Verify CRM Logo After Logged in!");
        boolean flag =  loginPage.validateCRMImage();
        Assert.assertTrue(flag);
    }

    @Test(priority = 3)
    // properties are coming dynamically
    public void loginTest() {
        System.out.println("Verify Login!");
             homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Close Browser!");
        // this driver is coming from BaseClass(Parent class)
        driver.quit();
    }


}
