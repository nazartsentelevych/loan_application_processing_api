package com.example.loan_application_processing_api.services;

import com.example.loan_application_processing_api.entities.LoanApplication;
import com.example.loan_application_processing_api.entities.LoanApplicationChecklistItem;
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

    public Optional<LoanApplicationChecklistItem> findOne(Long id){
        return loanChecklistItemEntityRepository.findById(id);
    }


    public Optional<LoanApplicationChecklistItem> add(LoanCheckListItemAddPOJO loanCheckListItemAddPOJO){

        Optional<LoanApplication> loanApplication = loanEntityRepository
                .findById(loanCheckListItemAddPOJO.getChecklistTaskId());

        return loanApplication
                .map(loanApplicationFind -> {
                    LoanApplicationChecklistItem loanApplicationChecklistItem = new LoanApplicationChecklistItem();
                    loanApplicationChecklistItem.setCheckListTask(loanApplicationFind);
                    loanApplicationChecklistItem.setStatus(loanCheckListItemAddPOJO.getStatus());
                    loanApplicationChecklistItem.setNotes(loanCheckListItemAddPOJO.getNotes());
                    return Optional.ofNullable(loanChecklistItemEntityRepository.save(loanApplicationChecklistItem));
                })
                .orElseGet(Optional::empty);
    }
}
