package com.qa.pages;

import com.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactsPage extends BaseClass {
    @FindBy(xpath = "//td[contains(text(),'Contacts')]")
    WebElement contactsPageLabel;
    @FindBy(name = "contact_id")
    WebElement clickCheckbox;
    @FindBy(id = "first_name")
    WebElement firstName;
    @FindBy(id = "surname")
    WebElement lastName;
    @FindBy(name = "client_lookup")
    WebElement company;
    @FindBy(xpath = "//input[@type='submit' and @value='Save']")
    WebElement saveBtn;
    // access all elements/ object from contacts page by calling ContactsPage constructor
    public ContactsPage(){
        PageFactory.initElements(driver,this);
    }
    public boolean verifyContactsLabel(){
        // check whether contact label is displayed or not
        // by using isDisplayed()
       return contactsPageLabel.isDisplayed();
    }
    // this is generic one
    // we can use like this in our scripts
    public void  selectContactsByName(String name){
        driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
    }
    // I don't want to hardcore values
    // will get dynamically
    public void createNewContact(String title,String ftName, String ltName, String cmpany){

        // select title // fill the values such as ftName,ltName and so on.
        Select select = new Select(driver.findElement(By.name("title")));
        select.selectByVisibleText(title);
        firstName.sendKeys(ftName);
        lastName.sendKeys(ltName);
        company.sendKeys(cmpany);
        saveBtn.click();
    }
}
