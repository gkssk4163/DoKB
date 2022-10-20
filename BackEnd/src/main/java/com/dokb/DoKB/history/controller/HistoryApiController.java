package com.dokb.DoKB.history.controller;

import com.dokb.DoKB.history.domain.History;
import com.dokb.DoKB.history.domain.HistoryApi;
import com.dokb.DoKB.history.service.HistoryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryApiController {

	@Autowired
	private HistoryApiService historyApiService;

	@PostMapping("")
	public HistoryApi create(HistoryApi request) {
		return historyApiService.create(request);
	}

	@GetMapping("{id}")
	public HistoryApi read(@PathVariable(name = "id") Long id) {
		return historyApiService.read(id);
	}

	@PutMapping("")
	public HistoryApi update(HistoryApi request) {
		return historyApiService.update(request);
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		return historyApiService.delete(id);
	}

	@GetMapping("/all/{accountNumber}")
	public List<History> findAllByAccount(@PathVariable(name = "accountNumber") String accountNumber) {
		return historyApiService.findAllByAccount(accountNumber);
	}
}
