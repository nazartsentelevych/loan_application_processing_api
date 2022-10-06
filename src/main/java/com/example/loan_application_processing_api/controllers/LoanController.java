package com.example.loan_application_processing_api.controllers;

import com.example.loan_application_processing_api.entities.LoanEntity;
import com.example.loan_application_processing_api.pojo.LoanAddPOJO;
import com.example.loan_application_processing_api.pojo.LoanClosePOJO;
import com.example.loan_application_processing_api.services.LoanService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanEntity> findLoanId(@PathVariable long id){
        return loanService.findOne(id)
                .map(applicationEntity -> ResponseEntity.status(HttpStatus.OK).body(applicationEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content")
    })
    @GetMapping
    public ResponseEntity<List<LoanEntity>> findLoans(){
        List<LoanEntity> loanEntityEntities = loanService.findAll();
        return loanEntityEntities.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(loanService.findAll());
    }

    @PostMapping
    public ResponseEntity<LoanEntity> addLoan(@Valid @RequestBody LoanAddPOJO loanAddPOJO){
        return loanService.add(loanAddPOJO)
                .map(loanApplication -> ResponseEntity.status(HttpStatus.CREATED).body(loanApplication))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping("/open/{id}")
    public ResponseEntity<LoanEntity> openLoan(@PathVariable long id){
        return loanService.openLoan(id)
                .map(loanApplication -> ResponseEntity.status(HttpStatus.OK).body(loanApplication))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(null));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping("/close/{id}")
    public ResponseEntity<LoanEntity> closeLoan(@Valid @RequestBody LoanClosePOJO loanClosePOJO, @PathVariable long id){
        return loanService.closeLoan(id, loanClosePOJO)
                .map(loanApplication -> ResponseEntity.status(HttpStatus.OK).body(loanApplication))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(null));
    }

}
