package com.qcynergy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qcynergy.baseTest.BaseTest;

public class ProgramDashboard extends BaseTest{

	@FindBy(xpath="//*[contains(@class,'mx-name-bootstrapMultiSelect4')]/div/div/button")
	WebElement allOpenDealsDropDown;
	
	@FindBy(xpath="//*[contains(@class,'mx-name-bootstrapMultiSelect4')]/div/div/ul/li[2]/a//input")
	WebElement seletAllOpenDealsDropDown;
	
	@FindBy(xpath="//*[contains(@class,'mx-name-bootstrapMultiSelect4')]/div/div/ul/li[1]/div/input")
	WebElement searchOpenDealsDropDown;
	
	@FindBy(xpath="//*[contains(@class,'mx-name-progressBar5')]/div/span[1]")
	WebElement countIntRvwComp;
	
	@FindBy(xpath="//*[contains(@class,'mx-name-label1')]")
	WebElement tradeStatusTitle;
	
	public ProgramDashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickallOpenDealsDropDown(){
		allOpenDealsDropDown.click();
	}
	public void clickTradeStatusTitle(){
		tradeStatusTitle.click();
	}
	public void clickseletAllOpenDealsDropDown(){
		seletAllOpenDealsDropDown.click();
	}
	public void sendsearchOpenDealsDropDown(String strSearchOpenDealsDropDown){
		searchOpenDealsDropDown.sendKeys(strSearchOpenDealsDropDown);
	}
	public String getcountIntRvwComp(){
		return countIntRvwComp.getText();
	}
}
