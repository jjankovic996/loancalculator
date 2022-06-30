package com.leanpay.com.loancalculator.service;

import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;

public interface LoanService {
    ResponseWrapper calculateLoan(LoanCalculatorRequest request);
}
