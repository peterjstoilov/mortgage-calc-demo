package com.mortgagecalc.pages;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// objects will not be created from this class, hence it is abstract
// all other pages will be able to inherit the base page variables and methods
public abstract class BasePage {

	protected WebDriver driver;

	// constructor with WebDriver as argument
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	// to enter data in an element we type in the string
	protected void type(String text, By locator) {
		// if there is already data, we clear it first
		find(locator).clear();
		find(locator).sendKeys(text);
	}

	// choosing dropdown option by value attribute
	protected void selectByValue(String selectValue, By locator) {
		Select dropdown = new Select(find(locator));
		dropdown.selectByValue(selectValue);
	}

	// choosing dropdown option by visible text
	protected void selectByVisibleText(String selectValue, By locator) {
		Select dropdown = new Select(find(locator));
		dropdown.selectByVisibleText(selectValue);
	}

	// choosing dropdown option by index value
	protected void selectByIndex(int index, By locator) {
		Select dropdown = new Select(find(locator));
		dropdown.selectByIndex(index);
	}

	protected void click(By locator) {
		if (find(locator).isDisplayed()) {
			find(locator).click();
		}
	}

	protected Boolean isDisplayed(By locator) {
		try {
			return find(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected float convertStringToFloat(String inputString) {
		// convert e.g "total $20,921.24" => 20921.24 (ready for float parse)
		float outputFloat = Float
				.parseFloat(inputString.replaceAll("[a-zA-Z $,]", ""));
		return outputFloat;
	}

	protected void waitForCalculation(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Pattern pattern = Pattern.compile("\\d"); // looking for any digits
		wait.until(ExpectedConditions.textMatches(locator, pattern)); // waits until string contains a digit
	}

}
