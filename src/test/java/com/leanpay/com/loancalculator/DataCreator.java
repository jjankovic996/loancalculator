package com.leanpay.com.loancalculator;

import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;

public class DataCreator {
    public static LoanCalculatorRequest createLoanCalculatorRequest(
            Double loanAmount, Integer interestRate, Integer numberOfPayments, String paymentFrequency) {
        LoanCalculatorRequest request = new LoanCalculatorRequest();
        request.setLoanAmount(loanAmount);
        request.setInterestRate(interestRate);
        request.setNumberOfPayments(numberOfPayments);
        request.setPaymentFrequency(paymentFrequency);

        return request;
    }
}
