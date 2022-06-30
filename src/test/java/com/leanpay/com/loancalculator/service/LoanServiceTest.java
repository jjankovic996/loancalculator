package com.leanpay.com.loancalculator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoanServiceTest {

    @Autowired
    LoanServiceImpl loanService;

//    @Test
//    public void test(){
//        LoanCalculatorRequest request
//                = LoanCalculatorRequest.builder()
//                        .loanAmount(1000d)
//                                .interestRate(5)
//                .loanTerm(10)
//        .build();
//        double payment = loanService.calculateLoan(request);
//        System.out.println(payment);
//        assertTrue(true);
//
//        System.out.println(loanService.calculateTotalInterest(request.getLoanAmount(), payment, 10));
//    }
}
