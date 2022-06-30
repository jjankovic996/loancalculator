package com.leanpay.com.loancalculator.service;

import com.leanpay.com.loancalculator.DataCreator;
import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import com.leanpay.com.loancalculator.entity.LoanCalculationEntity;
import com.leanpay.com.loancalculator.enums.PaymentFrequencyEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanServiceHelperTest {

  @Autowired private LoanServiceHelper loanServiceHelper;

  @Test
  public void validateRequestTest() {
    LoanCalculatorRequest requestNoLoanAmount =
        DataCreator.createLoanCalculatorRequest(null, 5, 10, "MONTHLY");
    LoanCalculatorRequest requestNoInterestRate =
        DataCreator.createLoanCalculatorRequest(1000d, null, 10, "MONTHLY");
    LoanCalculatorRequest requestNoNumberOfPayments =
        DataCreator.createLoanCalculatorRequest(1000d, 5, null, "MONTHLY");
    LoanCalculatorRequest requestNoPaymentFrequency =
        DataCreator.createLoanCalculatorRequest(1000d, 5, 10, null);
    LoanCalculatorRequest requestWrongPaymentFrequency =
        DataCreator.createLoanCalculatorRequest(1000d, 5, 10, "somethingWrong");
    LoanCalculatorRequest requestAllGood =
        DataCreator.createLoanCalculatorRequest(1000d, 5, 10, "MONTHLY");

    ResponseWrapper responseNoLoanAmount = loanServiceHelper.validateRequest(requestNoLoanAmount);
    ResponseWrapper responseNoInterestRate =
        loanServiceHelper.validateRequest(requestNoInterestRate);
    ResponseWrapper responseNoNumberOfPayments =
        loanServiceHelper.validateRequest(requestNoNumberOfPayments);
    ResponseWrapper responseNoPaymentFrequency =
        loanServiceHelper.validateRequest(requestNoPaymentFrequency);
    ResponseWrapper responseWrongPaymentFrequency =
        loanServiceHelper.validateRequest(requestWrongPaymentFrequency);
    ResponseWrapper responseAllGood = loanServiceHelper.validateRequest(requestAllGood);

    assertEquals(Boolean.FALSE, responseNoLoanAmount.getSuccessful());
    assertEquals("Please enter loan amount.", responseNoLoanAmount.getErrorMessage());

    assertEquals(Boolean.FALSE,responseNoInterestRate.getSuccessful());
    assertEquals("Please enter interest rate.", responseNoInterestRate.getErrorMessage());

    assertEquals( Boolean.FALSE, responseNoNumberOfPayments.getSuccessful());
    assertEquals("Please enter number of payments.", responseNoNumberOfPayments.getErrorMessage());

    assertEquals(Boolean.FALSE, responseNoPaymentFrequency.getSuccessful());
    assertEquals("Please enter payment frequency.", responseNoPaymentFrequency.getErrorMessage());

    assertEquals(Boolean.FALSE, responseWrongPaymentFrequency.getSuccessful());
    assertEquals("Payment frequency is not valid. Please choose years or months.", responseWrongPaymentFrequency.getErrorMessage());

    assertEquals(Boolean.TRUE, responseAllGood.getSuccessful());
  }

  @Test
  public void getTotalNumberOfMonths() {
    LoanCalculatorRequest request = DataCreator.createLoanCalculatorRequest(null,null, 10, null);

    double months = loanServiceHelper.getTotalNumberOfMonths(PaymentFrequencyEnum.MONTHLY, request);
    double monthsFromYears = loanServiceHelper.getTotalNumberOfMonths(PaymentFrequencyEnum.ANNUALLY, request);

    assertEquals(10, months);
    assertEquals(120, monthsFromYears);
  }

  @Test
  public void createLoanCalculation(){
    LoanCalculatorRequest request =
            DataCreator.createLoanCalculatorRequest(1000d, 5, 10, "MONTHLY");

    LoanCalculationEntity entity = loanServiceHelper.createLoanCalculation(request, 102.31, 231);

    assertEquals(1000, entity.getLoanAmount());
    assertEquals(5, entity.getInterestRate());
    assertEquals(10, entity.getNumberOfPayments());
    assertEquals(PaymentFrequencyEnum.MONTHLY.name(), entity.getPaymentFrequency().name());
    assertEquals(102.31, entity.getMonthlyPayment());
    assertEquals(231, entity.getTotalInterest());
  }

  @Test
  public void calculateTotalInterest() {
    double totalInterest = loanServiceHelper.calculateTotalInterest(1000, 123, 10);
    assertEquals(230.0, totalInterest);
  }


  @Test
  public void calculatePayment() {
    LoanCalculatorRequest request =
            DataCreator.createLoanCalculatorRequest(1000d, 5, 10, "MONTHLY");

    double payment = loanServiceHelper.calculatePayment(request, 8d);

    assertEquals(127.35511934605665, payment);
  }

}
