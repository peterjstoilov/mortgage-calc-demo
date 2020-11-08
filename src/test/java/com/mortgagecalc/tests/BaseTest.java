package com.mortgagecalc.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mortgagecalc.pages.CalculatorPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	// This class is for creating and tearing down the WebDriver
	private WebDriver driver;
	// base URL will not change, hence it will be final
	private final String baseURL = "https://www.mortgageloan.com/calculator";
	// Each test must be able to access the page objects via the base test:
	protected CalculatorPage calculatorPage;

	@BeforeEach
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		// for specific webdriver version use:
		// e.g. WebDriverManager.chromedriver().version(87.0).setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);

		calculatorPage = new CalculatorPage(driver);

	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}
}

/*
 * BACKUP: To execute on other browsers:
 */
// Edge:
//WebDriverManager.edgedriver().setup();
// for specific webdriver version use:
// e.g. WebDriverManager.chromedriver().version(87.0).setup();
//driver = new EdgeDriver();
//driver.manage().window().maximize();
//driver.get(baseURL);
//
// Firefox:
//WebDriverManager.firefoxdriver().setup();
// for specific webdriver version use:
// e.g. WebDriverManager.chromedriver().version(87.0).setup();
//driver=new FirefoxDriver();
//driver.manage().window().maximize();
//driver.get(baseURL);