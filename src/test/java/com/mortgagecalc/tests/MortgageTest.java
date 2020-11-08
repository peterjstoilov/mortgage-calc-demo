package com.mortgagecalc.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MortgageTest extends BaseTest {

	@Test
	@DisplayName("Calculate Mortgage based on Loan Amount + Term in Years + Interest Rate")
	public void calculateMortgageLoanPayments() {

		/*
		 * Test Scenario:
		 */
		// Using the �Mortgage Payment Calculator� and given input values as follows:
		// Loan Amount: $200,000
		final int loanAmount = 200000;
		// Term in years: 30 years
		final String termInYears = "30";
		// Interest rate: 5 %
		final int interestRate = 5;
		// Report_Amortization: Annually
		final String reportAmortization = "Annually";

		// Verify the following data on the results page:
		// Monthly Payment: $1,073.64
		final String monthlyPayment = "$1,073.64";
		// Total Payments: $386,513
		final String totalPayments = "Total Payments $386,513";
		// Total Interest: $186,513
		final String totalInterest = "Total Interest $186,513";

		// Pre-condition (not part of test assignment): close pop up message
		calculatorPage.closePopUpMessage();

		// Test Steps:
		calculatorPage.setLoanAmountField(loanAmount);
		calculatorPage.setTermInYearsSelect(termInYears);
		calculatorPage.setInterestRate(interestRate);
		calculatorPage.setReportAmortizationRate(reportAmortization);

		// Expected Results (Assertions):
		Assertions.assertTrue(calculatorPage.getMonthlyPaymentValue().equals(monthlyPayment));
		Assertions.assertTrue(calculatorPage.getTotalPaymentsValue().equals(totalPayments));
		Assertions.assertTrue(calculatorPage.getTotalInterestValue().equals(totalInterest));
	}
}
