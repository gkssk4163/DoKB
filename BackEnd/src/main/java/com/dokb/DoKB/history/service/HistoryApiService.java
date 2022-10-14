package com.dokb.DoKB.history.service;

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

    public HistoryApi create(HistoryApi request){
        History history = History.builder()
                .opponentAccount(request.getOpponentAccount())
                .dealDate(LocalDateTime.now())
                .inOut(request.getInOut())
                .amount(request.getAmount())
                .balance(request.getBalance())
                .accountNumber(request.getAccountNumber())
                .build();

        History newHistory = historyRepository.save(history);
        return response(newHistory);
    }

    public Optional<HistoryApi> read(Long id){
        Optional<History> optional = historyRepository.findById(id);
        return optional.map(history -> response(history));
    }

    public Optional<HistoryApi> update(HistoryApi request){
        Optional<History> optional = historyRepository.findById(request.getId());

        return optional.map(history -> {
            history.setOpponentAccount(request.getOpponentAccount())
                    .setDealDate(request.getDealDate())
                    .setInOut(request.getInOut())
                    .setAmount(request.getAmount())
                    .setBalance(request.getBalance())
                    .setOpponentAccount(request.getOpponentAccount());
            return history;
        })
                .map(history -> historyRepository.save(history))
                .map(updateHistory->response(updateHistory));
    }

    public String delete(Long id){
        Optional<History> optional = historyRepository.findById(id);

        return optional.map(history -> {
            historyRepository.delete(history);
            return "Delete";
        }).orElseGet(()->"nodata");
    }
    private HistoryApi response(History history){
        HistoryApi historyApi = HistoryApi.builder()
                .id(history.getId())
                .opponentAccount(history.getOpponentAccount())
                .dealDate(history.getDealDate())
                .inOut(history.getInOut())
                .amount(history.getAmount())
                .balance(history.getBalance())
                .accountNumber(history.getAccountNumber())
                .build();

        return historyApi;
    }
}
