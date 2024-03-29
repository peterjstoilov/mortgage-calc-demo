package com.mortgagecalc.pages;

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

	public void setTermInYearsSelect(int termInYears) {
		selectByValue(String.valueOf(termInYears), termInYearsSelect);
	}

	public void setInterestRate(float interestRate) {
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
	public float getMonthlyPaymentValue() {
		// wait for the field to load the text value and then extract text
		waitForCalculation(monthlyPaymentValue);

		// extract value (string) and convert it to float
		String monthlyPaymentValueString = find(monthlyPaymentValue).getText(); // e.g. String="$1,073.64"
		float monthlyPaymentValue = convertStringToFloat(monthlyPaymentValueString);

		return monthlyPaymentValue;
	}

	public float getTotalPaymentsValue() {
		// wait for the field to load the text value and then extract text
		waitForCalculation(totalPaymentsAndInterestValue);

		// get total payments and interest text field
		String totalPaymentsAndInterestValueString = find(totalPaymentsAndInterestValue).getText();
		// split string and get first value (total payments)
		String[] totalPaymentsAndInterestValueStringArray = totalPaymentsAndInterestValueString.split("(\\r\\n|\\r|\\n)");
		// return first part of string array (total payments)
		String totalPaymentsString = totalPaymentsAndInterestValueStringArray[0];

		// convert to float
		float totalPaymentsFloat = convertStringToFloat(totalPaymentsString);

		return totalPaymentsFloat;
	}

	public float getTotalInterestValue() {
		// wait for the field to load the text value and then extract text
		waitForCalculation(totalInterestValue);

		// get string value
		String totalInterestValueString = find(totalInterestValue).getText();

		// convert to float
		float totalInterestValueFloat = convertStringToFloat(totalInterestValueString);
		return totalInterestValueFloat;
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
