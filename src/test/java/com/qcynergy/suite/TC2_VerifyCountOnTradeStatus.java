package com.qcynergy.suite;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qcynergy.baseTest.BaseTest;
import com.qcynergy.database.SQLConnector;
import com.qcynergy.pages.Login;
import com.qcynergy.pages.ProgramDashboard;

public class TC2_VerifyCountOnTradeStatus extends BaseTest{

	public static BaseTest func;
    Login login ;
    ProgramDashboard pd;
    SQLConnector sqlconnector;

	@BeforeTest
	public  void setUp() throws Exception {
		func = new BaseTest();
		func.fn_readProperties();
		func.fn_LoadBrowser();
		func.fn_maxWindow();
		login = new Login(driver);
		pd=new ProgramDashboard(driver);
		sqlconnector=new SQLConnector();
	}

	@Test(description = "", dataProvider = "LoginUser" ,priority=1)
	public void Test_Successfull_Login(String user, String pass) throws InterruptedException{
		
			login.setUserName(user);
			login.setPassword(pass);
			login.clickLogin();
			login.waitForHomePage();
			pd.clickallOpenDealsDropDown();
			Thread.sleep(3000);
			pd.clickseletAllOpenDealsDropDown();
			Thread.sleep(3000);
			pd.sendsearchOpenDealsDropDown(properties.getProperty("DealName"));
			Thread.sleep(3000);
			pd.clickseletAllOpenDealsDropDown();
			Thread.sleep(6000);
			pd.clickTradeStatusTitle();
			Assert.assertEquals(pd.getcountIntRvwComp(),sqlconnector.InitialRvwComplete());
			login.clickLogoutLink();
	}
	@DataProvider
	public Object[][] LoginUser(){

		return new Object[][] { { "ss_seller2", "Digital@123" } };
	}
	
	 @AfterTest
	 public void afterTest(){
		 /*driver.close();*/
	 }

}
