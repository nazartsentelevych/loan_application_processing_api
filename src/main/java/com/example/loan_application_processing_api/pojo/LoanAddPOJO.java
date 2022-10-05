package com.example.loan_application_processing_api.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoanAddPOJO {
    @NotBlank(message = "applicantName not will be blank")
    @Length(max=100, message = "Max length for applicantName field 100")
    private String applicantName;
}
