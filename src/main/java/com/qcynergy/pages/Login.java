package com.qcynergy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qcynergy.baseTest.BaseTest;

public class Login extends BaseTest{
	WebDriver driver;
	
	@FindBy(id="usernameInput")
	WebElement userName;
	
	@FindBy(id="passwordInput")
	
	WebElement password;
	
	@FindBy(css=".btn.mx-name-signOutButton")
	WebElement logoutLink;
	
	@FindBy(xpath="//a[contains(.,' Program Dashboard')]")
	WebElement homeTab;
	
	public Login(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);

	}
	
	//InputFieds
		public void setUserName(String user){
			waitForElement(driver, 30, userName);
			userName.sendKeys(user);
		}
		public void setPassword(String pass){
			password.sendKeys(pass);
		}
		public void clickLogin(){
			password.submit();
		}
		public void login(String user,String pass){
			setUserName(user);
			setPassword(pass);
			clickLogin();
			waitForElement(driver,120, homeTab);
		}
		public void waitForHomePage(){
			waitForElement(driver,120, homeTab);
		}
		public void clickLogoutLink(){
			logoutLink.click();
			waitForElement(driver, 120, userName);
		}
}
