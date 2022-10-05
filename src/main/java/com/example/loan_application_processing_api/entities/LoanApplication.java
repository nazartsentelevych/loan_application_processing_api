package com.example.loan_application_processing_api.entities;

import com.example.loan_application_processing_api.enums.entity.Loan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@NoArgsConstructor
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="BIGINT")
    private Long loanApplicationId;
    @Column(length = 100)
    @NotNull
    private String applicantName;
    @Column(length = 10)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Loan status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "checkListTask")
    @JsonManagedReference
    private Set<LoanApplicationChecklistItem> loanApplicationChecklistItems;


}