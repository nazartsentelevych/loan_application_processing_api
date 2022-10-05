package com.example.loan_application_processing_api.controllers;

import com.example.loan_application_processing_api.entities.LoanApplicationChecklistItem;
import com.example.loan_application_processing_api.pojo.LoanCheckListItemAddPOJO;
import com.example.loan_application_processing_api.services.LoanCheckListItemService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/loanchecklist")
public class LoanChecklistItem {

    private final LoanCheckListItemService loanCheckListItemService;

    @Autowired
    public LoanChecklistItem(LoanCheckListItemService loanCheckListItemService) {
        this.loanCheckListItemService = loanCheckListItemService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Invalid object ID", content = @Content)
    })
    @PostMapping
    public ResponseEntity<LoanApplicationChecklistItem> addLoan(@Valid @RequestBody LoanCheckListItemAddPOJO loanCheckListItemAddPOJO){

        return loanCheckListItemService.add(loanCheckListItemAddPOJO)
                .map(loanApplicationChecklistItem -> ResponseEntity.status(HttpStatus.CREATED).body(loanApplicationChecklistItem))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

}
