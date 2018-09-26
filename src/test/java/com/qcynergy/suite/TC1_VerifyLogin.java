package com.qcynergy.suite;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qcynergy.baseTest.BaseTest;
import com.qcynergy.pages.Login;

public class TC1_VerifyLogin extends BaseTest{
	public static BaseTest func;

	Login login ;

	@BeforeTest
	public  void setUp() throws Exception {
		func = new BaseTest();
		func.fn_readProperties();
		func.fn_LoadBrowser();
		func.fn_maxWindow();
		login = new Login(driver);
	}

	// validate Login Page
	@Test(description = "Test Stearns Url" ,priority=0)
	public void Test_Qcynergy_Url() throws Exception {
			Assert.assertEquals(func.fn_GetTitle(), "QCynergy Portal");

	}

	// test SuccessFull login
	@Test(description = "test SuccessFull login", dataProvider = "LoginUser" ,priority=1)
	public void Test_Successfull_Login(String user, String pass) throws InterruptedException{
		
			login.setUserName(user);
			login.setPassword(pass);
			login.clickLogin();
			login.waitForHomePage();
			Assert.assertEquals(func.fn_GetTitle(), "QCynergy Portal - Home");
	}
	
	// test SuccessFull logout
	@Test(description = "test SuccessFull logout" ,priority=2)
	public void Test_Successfull_Logout() throws InterruptedException{

			login.clickLogoutLink();
			Assert.assertEquals(func.fn_GetTitle(), "QCynergy Portal");
	}

	@DataProvider
	public Object[][] LoginUser(){

		return new Object[][] { { "sahil.singh", "Digital123" } };
	}
	
	 @AfterTest
	 public void afterTest(){
		 /*driver.close();*/
	 }

}
