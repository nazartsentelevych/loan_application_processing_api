package com.example.loan_application_processing_api.services;

import com.example.loan_application_processing_api.entities.UserEntity;
import com.example.loan_application_processing_api.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.List;

@Component
public class LoadUserDetailsService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;
    @Autowired
    public LoadUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUsernameAndIsDeleted(userName, false);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
