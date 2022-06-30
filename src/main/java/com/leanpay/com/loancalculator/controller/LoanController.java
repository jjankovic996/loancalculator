package com.leanpay.com.loancalculator.controller;

import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import com.leanpay.com.loancalculator.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan/")
@RequiredArgsConstructor
public class LoanController{

    private final LoanService loanService;

    @PostMapping("calculate")
    public ResponseEntity<ResponseWrapper> calculateLoan(@RequestBody LoanCalculatorRequest request){
        return ResponseEntity.ok(loanService.calculateLoan(request));
    }

}
