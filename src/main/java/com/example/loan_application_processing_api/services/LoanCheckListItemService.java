package com.example.loan_application_processing_api.services;

import com.example.loan_application_processing_api.entities.LoanApplicationEntity;
import com.example.loan_application_processing_api.entities.LoanApplicationChecklistItemEntity;
import com.example.loan_application_processing_api.pojo.LoanCheckListItemAddPOJO;
import com.example.loan_application_processing_api.repositories.LoanChecklistItemEntityRepository;
import com.example.loan_application_processing_api.repositories.LoanEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanCheckListItemService {
    private final LoanChecklistItemEntityRepository loanChecklistItemEntityRepository;
    private final LoanEntityRepository loanEntityRepository;

    @Autowired
    public LoanCheckListItemService(LoanChecklistItemEntityRepository loanChecklistItemEntityRepository, LoanEntityRepository loanEntityRepository) {
        this.loanChecklistItemEntityRepository = loanChecklistItemEntityRepository;
        this.loanEntityRepository = loanEntityRepository;
    }

    public Optional<LoanApplicationChecklistItemEntity> findOne(Long id){
        return loanChecklistItemEntityRepository.findById(id);
    }


    public Optional<LoanApplicationChecklistItemEntity> add(LoanCheckListItemAddPOJO loanCheckListItemAddPOJO){

        Optional<LoanApplicationEntity> loanApplication = loanEntityRepository
                .findById(loanCheckListItemAddPOJO.getChecklistTaskId());

        return loanApplication
                .map(loanApplicationFind -> {
                    LoanApplicationChecklistItemEntity loanApplicationChecklistItemEntity = new LoanApplicationChecklistItemEntity();
                    loanApplicationChecklistItemEntity.setCheckListTask(loanApplicationFind);
                    loanApplicationChecklistItemEntity.setStatus(loanCheckListItemAddPOJO.getStatus());
                    loanApplicationChecklistItemEntity.setNotes(loanCheckListItemAddPOJO.getNotes());
                    return Optional.of(loanChecklistItemEntityRepository.save(loanApplicationChecklistItemEntity));
                })
                .orElseGet(Optional::empty);
    }
}
