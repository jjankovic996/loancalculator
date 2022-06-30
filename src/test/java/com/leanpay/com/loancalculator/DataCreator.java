package com.leanpay.com.loancalculator;

import com.leanpay.com.loancalculator.dto.LoanCalculationResponse;
import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;

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

    public static ResponseWrapper createResponseWrapper(LoanCalculationResponse response, Boolean successful, String errorMessage){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setResponse(response);
        responseWrapper.setSuccessful(successful);
        responseWrapper.setErrorMessage(errorMessage);

        return  responseWrapper;
    }
}
