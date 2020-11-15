package com.mortgagecalc.tests;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MortgageTest extends BaseTest {

	/*
	 * Test Case 1:
	 */
	// Using the Mortgage Payment Calculator and given input values as follows:
	// Loan Amount: $200,000
	// Term in years: 30 years
	// Interest rate: 5 %
	// Report_Amortization: Annually

	// Verify the following data on the results page:
	// Monthly Payment: $1,073.64
	// Total Payments: $386,513
	// Total Interest: $186,513

	// Set parameters for the test here:
	private static Stream<Arguments> parameterProvider() {
		return Stream.of(
				Arguments.of(200000, "30", 5, "Annually", 1073.64f, 386513, 186513), // TC1
				Arguments.of(200000, "30", 5, "Monthly", 1073.64f, 386513, 186513),
				Arguments.of(250000000, "15", 7, "Annually", 2247070.68f, 404472721, 154472721),
				Arguments.of(0, "40", 1, "Monthly", 0.00f, 0, 0),
				Arguments.of(249999999, "1", 25, "Annually", 23761050.72f, 285132609, 35132610),
				Arguments.of(1, "35", 0, "Monthly", 0.00f, 1, 0),
				Arguments.of(123000000, "25", 0.001f, "Monthly", 410051.42f, 123015427, 15427),
				Arguments.of(1500, "20", 24.999f, "Annually", 31.47f, 7563, 6063),
				Arguments.of(50000, "15", 5.87f, "Monthly", 418.42f, 75317, 25317));
	}

	// @Test
	@DisplayName("Calculate Mortgage based on Loan Amount + Term in Years + Interest Rate")
	@ParameterizedTest
	@MethodSource("parameterProvider")
	public void calculateMortgageLoanPayments(int loanAmount, String termInYears, float interestRate,
			String reportAmortization, float monthlyPayment, float totalPayments, float totalInterest) {

		// Pre-condition (not part of test assignment): close pop up message
		calculatorPage.closePopUpMessage();

		// Test Steps:
		calculatorPage.setLoanAmountField(loanAmount);
		calculatorPage.setTermInYearsSelect(termInYears);
		calculatorPage.setInterestRate(interestRate);
		calculatorPage.setReportAmortizationRate(reportAmortization);

		// Expected Results (Assertions):
		Assertions.assertEquals(calculatorPage.getMonthlyPaymentValue(), monthlyPayment);
		Assertions.assertEquals(calculatorPage.getTotalPaymentsValue(), totalPayments);
		Assertions.assertEquals(calculatorPage.getTotalInterestValue(), totalInterest);
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
