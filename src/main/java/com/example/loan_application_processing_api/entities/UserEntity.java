package com.example.loan_application_processing_api.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="BIGINT")
    private Long userId;
    @Column(length = 100)
    @NotNull
    @UniqueElements
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Boolean isAdmin;
    @NotNull
    private Boolean isDeleted;
}
