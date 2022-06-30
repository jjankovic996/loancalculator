package com.leanpay.com.loancalculator.service;

import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.enums.PaymentFrequencyEnum;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import com.leanpay.com.loancalculator.entity.LoanCalculationEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class LoanServiceHelper {

    public ResponseWrapper validateRequest(LoanCalculatorRequest request) {
        ResponseWrapper responseWrapper = new ResponseWrapper();

        if (!EnumUtils.isValidEnum(PaymentFrequencyEnum.class, request.getPaymentFrequency())) {
            log.info("Payment frequency is not valid. Please choose years or months.");

            responseWrapper.setSuccessful(Boolean.FALSE);
            responseWrapper.setErrorMessage("Payment frequency is not valid. Please choose years or months.");

            return responseWrapper;
        }

        if(request.getLoanAmount() == null){
            log.info("Please enter loan amount.");

            responseWrapper.setSuccessful(Boolean.FALSE);
            responseWrapper.setErrorMessage("Please enter loan amount.");

            return responseWrapper;
        }

        if(request.getInterestRate() == null){
            log.info("Please enter interest rate.");

            responseWrapper.setSuccessful(Boolean.FALSE);
            responseWrapper.setErrorMessage("Please enter interest rate.");

            return responseWrapper;
        }

        if(request.getNumberOfPayments() == null) {
            log.info("Please enter number of payments.");

            responseWrapper.setSuccessful(Boolean.FALSE);
            responseWrapper.setErrorMessage("Please enter number of payments.");

            return responseWrapper;
        }

        responseWrapper.setSuccessful(Boolean.TRUE);
        return responseWrapper;
    }

    public double calculatePayment(LoanCalculatorRequest request, Double numberOfMonths) {
        double interestRatePerMonth = request.getInterestRate() / 100d /12d;
        double i = Math.pow(1 + interestRatePerMonth, numberOfMonths);
        return  (request.getLoanAmount() * interestRatePerMonth * i ) / (i - 1) ;
    }

    public double getTotalNumberOfMonths(PaymentFrequencyEnum paymentFrequency, LoanCalculatorRequest request){
        return switch (paymentFrequency) {
            case   MONTHLY -> request.getNumberOfPayments();
            case   ANNUALLY -> request.getNumberOfPayments() * 12;
        };
    }


    public double calculateTotalInterest (double loanAmount, double payment, double numberOfMonths) {
        return  payment * numberOfMonths - loanAmount;
    }


    public LoanCalculationEntity createLoanCalculation(LoanCalculatorRequest request, double payment, double totalInterest) {
        LoanCalculationEntity entity = new LoanCalculationEntity();
        entity.setUuid(UUID.randomUUID().toString());
        entity.setLoanAmount(request.getLoanAmount());
        entity.setInterestRate(request.getInterestRate());
        entity.setNumberOfPayments(request.getNumberOfPayments());
        entity.setPaymentFrequency(PaymentFrequencyEnum.valueOf(request.getPaymentFrequency()));
        entity.setMonthlyPayment(payment);
        entity.setTotalInterest(totalInterest);

        return entity;
    }
}
