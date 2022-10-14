package com.dokb.DoKB.history.service;

import com.dokb.DoKB.account.domain.Account;
import com.dokb.DoKB.account.repository.AccountRepository;
import com.dokb.DoKB.common.ApiResponseStatus;
import com.dokb.DoKB.history.repository.HistoryRepository;
import com.dokb.DoKB.history.domain.History;
import com.dokb.DoKB.history.domain.HistoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class HistoryApiService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    AccountRepository accountRepository;
    public HistoryApi create(HistoryApi request){
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow(NullPointerException::new);
        History history = History.builder()
                .opponentAccount(request.getOpponentAccount())
                .dealDate(LocalDateTime.now())
                .inOut(request.getInOut())
                .amount(request.getAmount())
                .balance(request.getBalance())
                .account(account)
                .build();

        History newHistory = historyRepository.save(history);
        return response(newHistory);
    }

    public HistoryApi read(Long id){
        History history = historyRepository.findById(id).orElseThrow(NullPointerException::new);
        return response(history);
    }

    public HistoryApi update(HistoryApi request){
        History history = historyRepository.findById(request.getId()).orElseThrow(NullPointerException::new);
        history.setOpponentAccount(request.getOpponentAccount())
                .setDealDate(request.getDealDate())
                .setInOut(request.getInOut())
                .setAmount(request.getAmount())
                .setBalance(request.getBalance())
                .setOpponentAccount(request.getOpponentAccount());
        History updateHistory = historyRepository.save(history);
        return response(updateHistory);
    }

    public String delete(Long id){
        History history = historyRepository.findById(id).orElseThrow(NullPointerException::new);
        historyRepository.delete(history);
        return ApiResponseStatus.DELETE.getLabel();
    }
    private HistoryApi response(History history){
        HistoryApi historyApi = HistoryApi.builder()
                .id(history.getId())
                .opponentAccount(history.getOpponentAccount())
                .dealDate(history.getDealDate())
                .inOut(history.getInOut())
                .amount(history.getAmount())
                .balance(history.getBalance())
                .accountNumber(history.getAccount().getAccountNumber())
                .build();

        return historyApi;
    }
}
