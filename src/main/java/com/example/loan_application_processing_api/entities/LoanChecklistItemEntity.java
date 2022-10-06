package com.example.loan_application_processing_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "loan_application_checklist_item")
public class LoanChecklistItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="BIGINT")
    private Long loanApplicationId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "check_list_task")
    private LoanEntity checkListTask;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean status;
    @Column(length = 1000)
    private String notes;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private UserEntity userId;

}
