package com.example.loan_application_processing_api.controllers;

import com.example.loan_application_processing_api.entities.LoanApplication;
import com.example.loan_application_processing_api.pojo.LoanAddPOJO;
import com.example.loan_application_processing_api.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> findLoanId(@PathVariable long id){
        return loanService.findOne(id)
                .map(applicationEntity -> ResponseEntity.status(HttpStatus.OK).body(applicationEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    @GetMapping
    public ResponseEntity<List<LoanApplication>> findLoans(){
        List<LoanApplication> loanApplicationEntities = loanService.findAll();
        return loanApplicationEntities.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(loanService.findAll());
    }

    @PostMapping
    public ResponseEntity<LoanApplication> addLoan(@Valid @RequestBody LoanAddPOJO loanAddPOJO){
        return loanService.add(loanAddPOJO)
                .map(loanApplication -> ResponseEntity.status(HttpStatus.CREATED).body(loanApplication))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

}
