package com.example.loan_application_processing_api.repositories;

import com.example.loan_application_processing_api.entities.LoanApplicationChecklistItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanChecklistItemEntityRepository extends JpaRepository<LoanApplicationChecklistItemEntity, Long> {
}