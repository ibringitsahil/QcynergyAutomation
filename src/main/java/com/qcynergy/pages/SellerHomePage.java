package com.qcynergy.pages;

import javax.swing.Action;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qcynergy.baseTest.BaseTest;

public class SellerHomePage extends BaseTest{
	
	@FindBy(css =".mx-name-ea24db23-2bd4-55f1-be6f-5917cfb65ecf-4")
	WebElement myAccountTab;
	
	@FindBy(css =".btn.mx-name-microflowTrigger1")
	WebElement changePasswordButton;
	
	@FindBy(xpath ="//*[@id='mxui_widget_PasswordInput_0']/input")
	WebElement oldPasswordField;
	
	@FindBy(xpath ="//*[@id='mxui_widget_PasswordInput_1']/input")
	WebElement newPasswordField;
	
	@FindBy(xpath ="//*[@id='mxui_widget_PasswordInput_2']/input")
	WebElement confirmPasswordField;
	
	@FindBy(css =".btn.mx-name-microflowButton1")
	WebElement changeButton;
	
	@FindBy(css=".modal-body.mx-dialog-body > p")
	WebElement getErrorMsg;
	
	@FindBy(css=".btn.btn-primary")
	WebElement okayButtonOnErrorMsg;
	
	public SellerHomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public void click_myAccountTab(){
		waitForElement(driver, 20,myAccountTab);
		myAccountTab.click();
	}
	
	public void click_changePasswordButton(){
		waitForElement(driver, 20,changePasswordButton);
		changePasswordButton.click();
	}
	
	public void send_oldPasswordField(String str_oldPasswordField){
		try{
		oldPasswordField.click();
		oldPasswordField.sendKeys("Digital@1");
		Actions action =  new Actions(driver);
		action.moveToElement(oldPasswordField).click().sendKeys(str_oldPasswordField).build().perform();
		JavascriptExecutor ex = (JavascriptExecutor)driver;
		ex.executeScript("argument[0].value()='ab'",oldPasswordField);
		}
		catch (Exception e) {
			
		}
	}
	
	public void send_newPasswordField(String str_newPasswordField){
		newPasswordField.sendKeys(str_newPasswordField);
	}
	
	public void send_confirmPasswordField(String str_confirmPasswordField){
		confirmPasswordField.sendKeys(str_confirmPasswordField);
	}
	
	public void click_changeButton(){
		changeButton.click();
	}
	
	public String getValidationMsg(){
		return getErrorMsg.getText();
	}
	
	public void click_okayButtonOnErrorMsg(){
		okayButtonOnErrorMsg.click();
	}
}
