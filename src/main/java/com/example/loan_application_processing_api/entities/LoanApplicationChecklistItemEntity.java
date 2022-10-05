package com.example.loan_application_processing_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Getter
@Setter
@NoArgsConstructor
@Table(name = "loan_application_checklist_item")
public class LoanApplicationChecklistItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="BIGINT")
    private Long loanApplicationId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    private LoanApplicationEntity checkListTask;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean status;
    @Column(length = 1000)
    private String notes;

}
