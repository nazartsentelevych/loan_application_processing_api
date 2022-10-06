package com.example.loan_application_processing_api.repositories;

import com.example.loan_application_processing_api.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanEntityRepository extends JpaRepository<LoanEntity, Long> {
    LoanEntity findByLoanApplicationIdAndUserIdOrLoanApplicationIdAndUserId(Long id, Long userId, Long id2, Long user);
    List<LoanEntity> findAllByUserIdOrUserId(Long userId, Long user);

}