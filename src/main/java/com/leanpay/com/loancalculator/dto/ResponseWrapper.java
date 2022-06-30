package com.leanpay.com.loancalculator.dto;

import lombok.Data;

@Data
public class ResponseWrapper {
     private LoanCalculationResponse response;
     private Boolean successful;
     private String errorMessage;
}
