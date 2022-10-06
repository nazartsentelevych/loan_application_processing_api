package com.example.loan_application_processing_api.pojo;

import com.example.loan_application_processing_api.enums.entity.Loan;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoanClosePOJO {

    @NotNull(message = "status not will be null")
    private Loan status;

    @NotNull(message = "isComplete not will be null")
    @BooleanFlag
    private boolean isComplete;

    @NotBlank(message = "notes not will be blank")
    @Length(max=1000, message = "Max length for notes field 1000")
    private String notes;
}
