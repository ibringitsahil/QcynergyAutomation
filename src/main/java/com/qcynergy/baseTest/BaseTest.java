package com.qcynergy.baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formattable;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {

	public static WebDriver driver;

	public static Properties properties;

	public Actions action;

	public WebDriver getDriver() {
		return driver;
	}
	
	//Extent Reports
	public static ExtentReports extentReports = new ExtentReports(System.getProperty("user.dir") + "\\QcynergyRegressionReport.html",
			true);
	public static ExtentTest extentTest;

	// Reading Data from Properties
	public void fn_readProperties() throws Exception {
		File file1 = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qcynergy\\config\\TestData.properties");
		properties = new Properties();
		FileInputStream fis1 = new FileInputStream(file1);
		properties.load(fis1);
	}

	// This Method Starts Browser and Redirects to url
	public void fn_LoadBrowser() throws IOException {
		String url = properties.getProperty("url");
		String browserType = properties.getProperty("browser");
		switch (browserType) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			System.out.println("no browser defined");
			break;
		}
		driver.get(url);

	}

	public void getUrl() {
		String url = properties.getProperty("url");
		driver.get(url);
	}

	public String fn_GetTitle() {
		return driver.getTitle();
	}

	public void ImplicitWait(int timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);

	}

	public WebElement fn_CssSelector(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	// ZoomOut
	public void fn_zoomOut() {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).keyDown(Keys.SUBTRACT).keyUp(Keys.CONTROL).keyUp(Keys.SUBTRACT).perform();
	}

	// maximize window
	public void fn_maxWindow() {
		driver.manage().window().maximize();
	}

	// DropDown Functions
	public void fn_selectByValue(WebElement element, String Value) {
		Select select = new Select(element);
		select.selectByValue(Value);
	}

	public void fn_selectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	public void fn_selectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public boolean fn_IsDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void visibilityOfElementLocated(String text) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(text)));
	}


	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void scrollPageDown() {
		action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		/* jse.executeScript("arguments[0].scrollIntoView();",element); */
	}
	
	public String fn_TakeScreenshot() throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/stearns/TestBase/";
		File destFile = new File((String) reportDirectory + "/general_screenshots/"  + "_" + formater.format(calendar.getTime()) + ".png");
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, destFile);
		String dest=(String) reportDirectory + "/general_screenshots/" + "_" + formater.format(calendar.getTime()) + ".png";

		return dest;
		
	}
	
}
