package com.leanpay.com.loancalculator.dto;

import lombok.Data;

@Data
public class LoanCalculationResponse {
    Double monthlyPayment;
    Double totalInterestPaid;
}
