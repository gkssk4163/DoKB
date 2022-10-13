package com.dokb.DoKB.account.service;

import com.dokb.DoKB.account.domain.Account;
import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.account.repository.AccountRepository;
import com.dokb.DoKB.common.ApiResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	public AccountDto create(AccountDto accountDto) {
		Account account = Account.builder()
				.accountNumber(accountDto.getAccountNumber())
				.password(accountDto.getPassword())
				.purpose(accountDto.getPurpose())
				.sof(accountDto.getSof())
				.userRegisterNumber(accountDto.getUserRegisterNumber())
				.build();
		Account newAccount = accountRepository.save(account);
		return response(newAccount);
	}

	public AccountDto read(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(NullPointerException::new);
		return response(account);
	}

	public AccountDto update(AccountDto accountDto) {
		Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
				.orElseThrow(NullPointerException::new);

		account.setAccountNumber(accountDto.getAccountNumber())
				.setPassword(accountDto.getPassword())
				.setPurpose(accountDto.getPurpose())
				.setSof(accountDto.getSof())
				.setBalance(accountDto.getBalance())
				.setUserRegisterNumber(accountDto.getUserRegisterNumber());
		Account updateAccount = accountRepository.save(account);

		return response(updateAccount);
	}

	public String delete(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(NullPointerException::new);

		accountRepository.delete(account);

		return ApiResponseStatus.DELETE.getLabel();
	}

	private AccountDto response(Account account) {
		AccountDto accountDto = AccountDto.builder()
				.accountNumber(account.getAccountNumber())
				.balance(account.getBalance())
				.password(account.getPassword())
				.purpose(account.getPurpose())
				.sof(account.getSof())
				.userRegisterNumber(account.getUserRegisterNumber())
				.build();
		return accountDto;
	}
}
