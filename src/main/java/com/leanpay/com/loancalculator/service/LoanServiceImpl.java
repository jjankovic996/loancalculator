package com.leanpay.com.loancalculator.service;

import com.leanpay.com.loancalculator.dto.LoanCalculationResponse;
import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.enums.PaymentFrequencyEnum;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import com.leanpay.com.loancalculator.entity.LoanCalculationEntity;
import com.leanpay.com.loancalculator.repository.LoanCalculationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanServiceImpl implements  LoanService {
    private final LoanServiceHelper serviceHelper;
    private final LoanCalculationRepository repository;

    public ResponseWrapper calculateLoan(LoanCalculatorRequest request) {
        ResponseWrapper responseWrapper = serviceHelper.validateRequest(request);

        if (responseWrapper.getSuccessful().equals(Boolean.FALSE)) {
          return responseWrapper;
        }

        double numberOfMonths =
            serviceHelper.getTotalNumberOfMonths(request.getPaymentFrequency(), request);
        double payment = serviceHelper.calculatePayment(request, numberOfMonths);
        double totalInterest =
            serviceHelper.calculateTotalInterest(request.getLoanAmount(), payment, numberOfMonths);

        LoanCalculationEntity entity =
            serviceHelper.createLoanCalculation(request, payment, totalInterest);
        entity = repository.save(entity);
        log.info(
            "Loan calculation entity saved with id {} and uuid {}", entity.getId(), entity.getUuid());

        BigDecimal paymentRoundUp = new BigDecimal(payment).setScale(2, RoundingMode.UP);
        BigDecimal totalInterestRoundUp = new BigDecimal(totalInterest).setScale(2, RoundingMode.UP);

        LoanCalculationResponse response = new LoanCalculationResponse();
        response.setMonthlyPayment(paymentRoundUp.doubleValue());
        response.setTotalInterestPaid(totalInterestRoundUp.doubleValue());

        log.info("Loan calculation successful");
        responseWrapper.setResponse(response);
        responseWrapper.setSuccessful(true);

        return responseWrapper;
    }
}
