package com.leanpay.com.loancalculator.entity;

import com.leanpay.com.loancalculator.enums.PaymentFrequencyEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "loan_calculation")
public class LoanCalculationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "interest_rate")
    private Integer interestRate;

    @Column(name = "number_of_payments")
    private Integer numberOfPayments;

    @Column(name = "payment_frequency")
    @Enumerated(EnumType.STRING)
    private PaymentFrequencyEnum paymentFrequency;

    @Column(name = "monthly_payment")
    private Double monthlyPayment;

    @Column(name = "total_interest")
    private Double totalInterest;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
