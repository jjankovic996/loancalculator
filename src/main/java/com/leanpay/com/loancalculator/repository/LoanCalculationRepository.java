package com.leanpay.com.loancalculator.repository;

import com.leanpay.com.loancalculator.entity.LoanCalculationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanCalculationRepository extends JpaRepository<LoanCalculationEntity, Long> {
}
