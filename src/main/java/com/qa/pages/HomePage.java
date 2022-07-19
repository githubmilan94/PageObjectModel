package com.qa.pages;

import com.qa.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class HomePage extends BaseClass {

    // after successfully login username is available or not in home page
    //@FindBy(xpath = "//td[contains(text(),'User: mila kuilya')]")


    @FindBy(xpath = "//td[contains(text(),'User: mila kuilya')]")
    WebElement userNameLabel;

    @FindBy(xpath = "//a[contains(text(),'Contacts')]")
    WebElement contactsLink;

    @FindBy(xpath = "//a[contains(text(),'New Contact')]")
    WebElement newContactLink;
    @FindBy(xpath = "//a[contains(text(),'Deals')]")
    WebElement dealsLink;

    @FindBy(xpath = "//a[contains(text(),'Tasks')]")
    WebElement tasksLink;

    // Create constructor of homepage to initialize page objects
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // Actions / methods
    //first check page title whether it is redirects to expected page or not
    // make sure doing it by checking page title
    public String verifyHomePageTitle(){
      return driver.getTitle();
    }

    public ContactsPage clickOnContactsLink(){
        contactsLink.click();
        // after clicking on contacts it will return contacts page object
        return new ContactsPage();
    }
    public DealsPage clickOnDealsLink(){
        dealsLink.click();
        // after clicking on contacts it will return contacts page object
        return new DealsPage();
    }

    public TasksPage clickOnTasksLink(){
        tasksLink.click();
        // after clicking on contacts it will return contacts page object
        return new TasksPage();
    }

    public boolean verifyRightUserName(){
       return userNameLabel.isDisplayed();
    }

    // On Home Page if I hover mouse cursor on Contact link then New contact will get displayed
    // 1st make it visible then click on New Contact
    public void clickOnNewContactLink() throws InterruptedException {
        Actions action = new Actions(driver);
        // mouse hover   // compulsory use build() and perform()
        action.moveToElement(contactsLink).build().perform();
        // click newContactLink
        //Thread.sleep(2000);
        newContactLink.click();

    }

}
