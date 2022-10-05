package com.example.loan_application_processing_api.repositories;

import com.example.loan_application_processing_api.entities.LoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanEntityRepository extends JpaRepository<LoanApplicationEntity, Long> {
}