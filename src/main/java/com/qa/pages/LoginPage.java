package com.qa.pages;

import com.qa.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {

    // define page factory -- @FindBy
    // object repository(OR)
    @FindBy(name="username")
    WebElement username;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginBtn;
    @FindBy(xpath = "//a[contains(),'Sign Up']")
    WebElement SignUp;
    @FindBy(xpath = "//img[contains(@class,'img-responsive')]")
    WebElement logo;

    // Initialize all elements/ page objects
    // we can not write here LoginPage.class -- it will throw error, we have to write this
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    // actions / methods
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public boolean validateCRMImage(){
        return logo.isDisplayed();
    }

    public HomePage login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
        // after click on login it is redirecting to home page
        // that;s why returning HomePage Object
        return new HomePage();

    }

}
