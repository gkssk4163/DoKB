package com.dokb.DoKB.history.controller;

import com.dokb.DoKB.history.service.HistoryApiService;
import com.dokb.DoKB.history.domain.HistoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/history")
public class HistoryApiController {

    @Autowired
    private HistoryApiService historyApiService;

    @PostMapping("")
    public HistoryApi create(@RequestBody HistoryApi request){
        return historyApiService.create(request);
    }

    @GetMapping("{id}")
    public Optional<HistoryApi> read(@PathVariable(name="id") Long id){
        return historyApiService.read(id);
    }

    @PutMapping("")
    public Optional<HistoryApi> update(@RequestBody HistoryApi request){
        return historyApiService.update(request);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable(name = "id") Long id){
        return historyApiService.delete(id);
    }
}
