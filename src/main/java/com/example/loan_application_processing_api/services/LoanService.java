package com.example.loan_application_processing_api.services;

import com.example.loan_application_processing_api.enums.entity.Loan;
import com.example.loan_application_processing_api.pojo.LoanAddPOJO;
import com.example.loan_application_processing_api.entities.LoanApplication;
import com.example.loan_application_processing_api.repositories.LoanEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanEntityRepository loanEntityRepository;
    @Autowired
    public LoanService(LoanEntityRepository loanEntityRepository) {
        this.loanEntityRepository = loanEntityRepository;
    }

    public List<LoanApplication> findAll(){
        return loanEntityRepository.findAll();
    }

    public Optional<LoanApplication> findOne(Long id){
        return loanEntityRepository.findById(id);
    }

    public Optional<LoanApplication> add(LoanAddPOJO loanAddPOJO){
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setApplicantName(loanAddPOJO.getApplicantName());
        loanApplication.setStatus(Loan.PENDING);
        return Optional.ofNullable(loanEntityRepository.save(loanApplication));
    }
}
