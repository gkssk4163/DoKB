package com.dokb.DoKB.account.controller;

import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.account.domain.AccountTransferDto;
import com.dokb.DoKB.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	AccountService accountService;

	@PostMapping
	public AccountDto create(AccountDto accountDto) {
		return accountService.create(accountDto);
	}

	@GetMapping("/{account_number}")
	public AccountDto read(@PathVariable(name = "account_number") String accountNumber) {
		return accountService.read(accountNumber);
	}

	@PutMapping
	public AccountDto update(AccountDto accountDto) {
		return accountService.update(accountDto);
	}

	@DeleteMapping("/{account_number}")
	public String delete(@PathVariable(name = "account_number") String accountNumber) {
		return accountService.delete(accountNumber);
	}

	@PostMapping("/transfer")
	public String transfer(AccountTransferDto accountTransferDto) {
		return accountService.transfer(accountTransferDto);
	}

}
