package com.mortgagecalc.pages;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage extends BasePage {

	// input fields on this page:
	private By loanAmountField = By.id("KJE-LOAN_AMOUNT");
	private By termInYearsSelect = By.id("KJE-TERM");
	private By interestRateFieldYear = By.id("KJE-INTEREST_RATE");
	private By reportAmortizationRadioButtonAnnually = By.id("KJE-BY_YEAR1");
	private By reportAmortizationRadioButtonMonthly = By.id("KJE-BY_YEAR2");

	// output fields on this page:
	private By monthlyPaymentValue = By.cssSelector("#KJE-MONTHLY_PAYMENT.KJEOutputLabel");
	private By totalPaymentsAndInterestValue = By.cssSelector("#KJE-C-GRAPH1 h2");
	private By totalInterestValue = By.cssSelector("#KJE-C-GRAPH1 h2 .KJESubTitle");

	// error messages on this page:
	private By errorMessageMortgageAmount = By.cssSelector("#KJE-LB-LOAN_AMOUNT .KJEGuiErrorPopup");
	private By errorMessageInterestRate = By.cssSelector("#KJE-LB-INTEREST_RATE .KJEGuiErrorPopup");

	// buttons on this page:
	private By getYourRatesButton = By.cssSelector("#KJEFixed a");
	private By getYourFreeQuotePopUp = By.xpath("//*[@id=\"ModalCTA_ML_ElementSectionCTA3866\"]");
	private By getYourFreeQuotePopUpCloseButton = By.xpath("//*[@id=\"svg_bg_ML_ElementSectionCTA3866\"]/span");

	// using constructor to load from BasePage
	public CalculatorPage(WebDriver driver) {
		super(driver);
	}

	// Setter methods:
	public void setLoanAmountField(int loanAmmount) {
		type(String.valueOf(loanAmmount), loanAmountField);
	}

	public void setTermInYearsSelect(String termInYears) {
		selectByValue(termInYears, termInYearsSelect);
	}

	public void setInterestRate(int interestRate) {
		type(String.valueOf(interestRate), interestRateFieldYear);
	}

	public void setReportAmortizationRate(String reportAmortizationRate) {
		switch (reportAmortizationRate) {
		case "Annually":
			click(reportAmortizationRadioButtonAnnually);
		break;
	case "Monthly":
			click(reportAmortizationRadioButtonMonthly);
		break;
		default:
			click(reportAmortizationRadioButtonAnnually);
		break;
		}
	}

	// Getter methods:
	public String getMonthlyPaymentValue() {
		// wait for the field to load the text value and then extract text
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Pattern pattern = Pattern.compile("\\d"); // looking for any digits
		wait.until(ExpectedConditions.textMatches(monthlyPaymentValue, pattern)); // waits until string contains a digit
		String monthlyPaymentValueString = find(monthlyPaymentValue).getText(); // e.g. String="$1,073.64"
		return monthlyPaymentValueString;
	}

	public String getTotalPaymentsValue() {
		// wait for the field to load the text value and then extract text
		WebDriverWait wait = new WebDriverWait(driver, 20);
		// set pattern and wait until digits appear in text field
		Pattern pattern = Pattern.compile("\\d");
		wait.until(ExpectedConditions.textMatches(totalPaymentsAndInterestValue, pattern));
		// get total payments and interest text field
		String totalPaymentsAndInterestValueString = find(totalPaymentsAndInterestValue).getText();
		// split string and get first value (total payments)
		String[] totalPaymentsAndInterestValueStringArray = totalPaymentsAndInterestValueString.split("(\\r\\n|\\r|\\n)");
		// return first part of string array (total payments)
		return totalPaymentsAndInterestValueStringArray[0];
	}

	public String getTotalInterestValue() {
		// wait for the field to load the text value and then extract text
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Pattern pattern = Pattern.compile("\\d"); // looking for any digits
		wait.until(ExpectedConditions.textMatches(totalInterestValue, pattern)); // waits until string contains a digit
		String totalInterestValueString = find(totalInterestValue).getText();
		return totalInterestValueString;
	}

	// *NOT PART OF TEST EXERCISE*
	// backup code not part of the task:
	public String getErrorMessageMortageAmount() {
		return find(errorMessageMortgageAmount).getText();
	}

	public String getErrorMesageInterestRate() {
		return find(errorMessageInterestRate).getText();
	}

	// *NOT PART OF TEST EXERCISE*
	// Closing the pop up by waiting for the element and clicking close
	public void closePopUpMessage() {
		waitForPopUp(getYourFreeQuotePopUp);
		click(getYourFreeQuotePopUpCloseButton);
	}

	private void waitForPopUp(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// *NOT PART OF TEST EXERCISE*
	// transition methods can go here:
	// convenience method for getting the rates (transitions to Quiz Page)
	public QuizPage clickGetYourRatesButton() {
		click(getYourRatesButton);
		return new QuizPage(driver);
	}

}
