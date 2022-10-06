package com.example.loan_application_processing_api.services;

import com.example.loan_application_processing_api.entities.LoanChecklistItemEntity;
import com.example.loan_application_processing_api.entities.UserEntity;
import com.example.loan_application_processing_api.enums.entity.Loan;
import com.example.loan_application_processing_api.pojo.LoanAddPOJO;
import com.example.loan_application_processing_api.entities.LoanEntity;
import com.example.loan_application_processing_api.pojo.LoanClosePOJO;
import com.example.loan_application_processing_api.repositories.LoanChecklistItemEntityRepository;
import com.example.loan_application_processing_api.repositories.LoanEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanService {
    private final LoanEntityRepository loanEntityRepository;
    private final LoanChecklistItemEntityRepository loanChecklistItemEntityRepository;
    private final UserService userService;
    @Autowired
    public LoanService(LoanEntityRepository loanEntityRepository, LoanChecklistItemEntityRepository loanChecklistItemEntityRepository, UserService userService) {
        this.loanEntityRepository = loanEntityRepository;
        this.loanChecklistItemEntityRepository = loanChecklistItemEntityRepository;
        this.userService = userService;
    }

    public List<LoanEntity> findAll(){
        UserEntity authUser = userService.getAuthUser();
        return loanEntityRepository.findAllByUserIdOrUserId(authUser.getUserId(), null);
    }

    public Optional<LoanEntity> findOne(Long id){
        return loanEntityRepository.findById(id);
    }

    public Optional<LoanEntity> add(LoanAddPOJO loanAddPOJO){
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setApplicantName(loanAddPOJO.getApplicantName());
        loanEntity.setStatus(Loan.PENDING);
        return Optional.of(loanEntityRepository.save(loanEntity));
    }

    public Optional<LoanEntity> openLoan(Long id){
        UserEntity authUser = userService.getAuthUser();
        LoanEntity loanEntity = loanEntityRepository
                .findByLoanApplicationIdAndUserIdOrLoanApplicationIdAndUserId(id, authUser.getUserId(), id,null);
        if(loanEntity == null) {
            return Optional.empty();
        }
        loanEntity.setStatus(Loan.PENDING);
        loanEntity.setUserId(authUser.getUserId());
        return Optional.of(loanEntityRepository.save(loanEntity));
    }

    public Optional<LoanEntity> closeLoan(Long id, LoanClosePOJO loanClosePOJO){
        UserEntity authUser = userService.getAuthUser();
        LoanEntity loanEntity = loanEntityRepository
                .findByLoanApplicationIdAndUserIdOrLoanApplicationIdAndUserId(id, authUser.getUserId(), id,null);

        if(loanEntity == null) {
            return Optional.empty();
        }

        loanEntity.setStatus(loanClosePOJO.getStatus());
        loanEntity.setUserId(null);

        LoanChecklistItemEntity loanChecklistItemEntity = new LoanChecklistItemEntity();
        loanChecklistItemEntity.setCheckListTask(loanEntity);
        loanChecklistItemEntity.setStatus(loanClosePOJO.isComplete());
        loanChecklistItemEntity.setNotes(loanClosePOJO.getNotes());
        loanChecklistItemEntity.setUserId(authUser);
        loanChecklistItemEntityRepository.save(loanChecklistItemEntity);

        return Optional.of(loanEntityRepository.save(loanEntity));
    }
}
