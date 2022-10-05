package com.example.loan_application_processing_api.services;

import com.example.loan_application_processing_api.entities.UserEntity;
import com.example.loan_application_processing_api.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserEntityRepository userEntityRepository;
    @Autowired
    public UserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public UserEntity getAuthUser(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userEntityRepository.findByUsername(user.getUsername());
    }
}
