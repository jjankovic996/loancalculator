package com.leanpay.com.loancalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoanCalculatorRequest {
    private Double loanAmount;
    private Integer interestRate;
    private Integer numberOfPayments; //2500 max
    private String paymentFrequency; // years or months


}
