package com.example.loan_application_processing_api.repositories;

import com.example.loan_application_processing_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndIsDeleted(String userName, Boolean isDeleted);
    UserEntity findByUsername(String userName);
}