package com.mortgagecalc.tests;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MortgageTest extends BaseTest {

	// Set parameters for the test here:
	private static Stream<Arguments> parameterProvider() {
		return Stream.of(
				Arguments.of(200000, "30", 5, "Annually", "$1,073.64", "Total Payments $386,513", "Total Interest $186,513"),
				Arguments.of(200000, "30", 5, "Monthly", "$1,073.64", "Total Payments $386,513", "Total Interest $186,513"));
	}

	// @Test
	@DisplayName("Calculate Mortgage based on Loan Amount + Term in Years + Interest Rate")
	@ParameterizedTest
	@MethodSource("parameterProvider")
	public void calculateMortgageLoanPayments(int loanAmount, String termInYears, int interestRate,
			String reportAmortization, String monthlyPayment, String totalPayments, String totalInterest) {

		/*
		 * Test Scenario 1:
		 */
		// Using the �Mortgage Payment Calculator� and given input values as follows:
		// Loan Amount: $200,000
		// Term in years: 30 years
		// Interest rate: 5 %
		// Report_Amortization: Annually

		// Verify the following data on the results page:
		// Monthly Payment: $1,073.64
		// Total Payments: $386,513
		// Total Interest: $186,513

		/*
		 * Test Scenario 2 (*NOT PART OF ASSIGNMENT*):
		 */
		// Using the �Mortgage Payment Calculator� and given input values as follows:
		// Loan Amount: $200,000
		// Term in years: 30 years
		// Interest rate: 5 %
		// Report_Amortization: Monthly

		// Verify the following data on the results page:
		// Monthly Payment: $1,073.64
		// Total Payments: $386,513
		// Total Interest: $186,513


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

/*
 * Backup (legacy) code for reference:
 */

// Printing:
//System.out.println("loanAmount = " + loanAmount);
//System.out.println("termInYears = " + termInYears);
//System.out.println("interestRate = " + interestRate);
//System.out.println("reportAmortization = " + reportAmortization);

//System.out.println("monthlyPayment = " + monthlyPayment);
//System.out.println("totalPayments = " + totalPayments);
//System.out.println("totalInterest = " + totalInterest);

// input parameters:
// final int loanAmount = 200000;
// final String termInYears = "30";
// final int interestRate = 5;
// final String reportAmortization = "Annually";
//
// output parameters:
// final String monthlyPayment = "$1,073.64";
// final String totalPayments = "Total Payments $386,513";
// final String totalInterest = "Total Interest $186,513";
