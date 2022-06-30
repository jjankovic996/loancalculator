package com.leanpay.com.loancalculator.controller;

import com.google.gson.Gson;
import com.leanpay.com.loancalculator.DataCreator;
import com.leanpay.com.loancalculator.controller.LoanController;
import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import com.leanpay.com.loancalculator.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoanController.class)

public class LoanControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private LoanService service;

  @InjectMocks private  LoanController controller;

  @Test
  public void calculateLoan() throws Exception {
    LoanCalculatorRequest loanRequest = DataCreator.createLoanCalculatorRequest(1000d,
            5, 10, "MONTHLY");

    when(service.calculateLoan(loanRequest)).thenReturn(DataCreator.createResponseWrapper(null, Boolean.TRUE, null));
    RequestBuilder request =
            MockMvcRequestBuilders.post("/api/v1/loan/calculate")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(new Gson().toJson(loanRequest));

    MvcResult result = mockMvc.perform(request).andReturn();

    verify(service, times(1)).calculateLoan(any());

    assertTrue(
        result
            .getResponse()
            .getContentAsString()
            .contains("{\"response\":null,\"successful\":true,\"errorMessage\":null}"));
  }
}
