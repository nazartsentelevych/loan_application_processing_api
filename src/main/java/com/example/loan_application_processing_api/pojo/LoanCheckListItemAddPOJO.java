package com.example.loan_application_processing_api.pojo;

import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoanCheckListItemAddPOJO {
    @NotNull(message = "checklistTask not will be null")
    private Long checklistTaskId;
    @NotNull
    @BooleanFlag
    private Boolean status;
    @NotNull
    @Length(max=1000, message = "Max length for notes field 1000")
    private String notes;
}
