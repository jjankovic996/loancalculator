package com.leanpay.com.loancalculator.integration;

import com.leanpay.com.loancalculator.DataCreator;
import com.leanpay.com.loancalculator.dto.LoanCalculatorRequest;
import com.leanpay.com.loancalculator.dto.ResponseWrapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoanControllerTest {

  @Autowired private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void initialiseRestAssuredMockMvcWebApplicationContext() {
    RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
  }

  @Test
  @Transactional
  public void testLoanCalculation() {
    LoanCalculatorRequest request =
        DataCreator.createLoanCalculatorRequest(1000d, 5, 10, "MONTHLY");
    ResponseWrapper responseWrapper =
        RestAssuredMockMvc.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/v1/loan//calculate")
            .then()
            .statusCode(200)
            .extract()
            .as(ResponseWrapper.class);

    assertNotNull(responseWrapper);
    assertTrue(responseWrapper.getSuccessful());
    assertNotNull(responseWrapper.getResponse());
  }

  @Test
  public void testLoanCalculationFail() {
    LoanCalculatorRequest request = DataCreator.createLoanCalculatorRequest(null, null, null, null);
    ResponseWrapper responseWrapper =
        RestAssuredMockMvc.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/v1/loan//calculate")
            .then()
            .statusCode(200)
            .extract()
            .as(ResponseWrapper.class);

    assertNotNull(responseWrapper);
    assertFalse(responseWrapper.getSuccessful());
    assertNull(responseWrapper.getResponse());
  }
}
