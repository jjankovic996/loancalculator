package com.leanpay.com.loancalculator.service;

import com.leanpay.com.loancalculator.DataCreator;
import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import com.leanpay.com.loancalculator.entity.LoanCalculationEntity;
import com.leanpay.com.loancalculator.repository.LoanCalculationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoanServiceTest {

  @Mock private LoanServiceHelper helper;

  @Mock private LoanCalculationRepository repository;

  @InjectMocks private LoanServiceImpl service;

  @Test
  public void calculateLoanTest() {
    LoanCalculatorRequest request =
        DataCreator.createLoanCalculatorRequest(1000d, 5, 10, "MONTHLY");

    when(helper.validateRequest(any()))
        .thenReturn(DataCreator.createResponseWrapper(null, Boolean.TRUE, null));
    when(helper.calculatePayment(any(), anyDouble())).thenReturn(2320.0799d);
    when(helper.getTotalNumberOfMonths(any(), any())).thenReturn(10d);
    when(helper.calculateTotalInterest(anyDouble(), anyDouble(), anyDouble()))
        .thenReturn(231.289098d);
    when(repository.save(any())).thenReturn(new LoanCalculationEntity());
    ResponseWrapper response = service.calculateLoan(request);

    verify(helper, times(1)).validateRequest(any());
    verify(helper, times(1)).calculatePayment(any(), anyDouble());
    verify(helper, times(1)).getTotalNumberOfMonths(any(), any());
    verify(helper, times(1)).calculateTotalInterest(anyDouble(), anyDouble(), anyDouble());
    verify(helper,times(1)).createLoanCalculation(any(), anyDouble(), anyDouble());
    verify(repository, times(1)).save(any());

    assertEquals(Boolean.TRUE, response.getSuccessful());
    assertNotNull(response.getResponse());
  }

  @Test
  public void calculateLoanTestFail() {
    LoanCalculatorRequest request =
            DataCreator.createLoanCalculatorRequest(null, null, null, null);

    when(helper.validateRequest(any()))
            .thenReturn(DataCreator.createResponseWrapper(null, Boolean.FALSE, "Error"));

    ResponseWrapper response = service.calculateLoan(request);

    verify(helper, times(1)).validateRequest(any());
    verify(helper, times(0)).calculatePayment(any(), anyDouble());
    verify(helper, times(0)).getTotalNumberOfMonths(any(), any());
    verify(helper, times(0)).calculateTotalInterest(anyDouble(), anyDouble(), anyDouble());
    verify(helper,times(0)).createLoanCalculation(any(), anyDouble(), anyDouble());
    verify(repository, times(0)).save(any());

    assertEquals(Boolean.FALSE, response.getSuccessful());
    assertNull(response.getResponse());
    assertNotNull(response.getErrorMessage());
  }
}
