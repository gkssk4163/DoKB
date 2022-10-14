package com.dokb.DoKB.account.service;

import com.dokb.DoKB.account.domain.Account;
import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.account.repository.AccountRepository;
import com.dokb.DoKB.common.ApiResponseStatus;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;
	public AccountDto create(AccountDto accountDto) {
		User user = userRepository.findFirstByRegisterNumber(accountDto.getUserRegisterNumber()).orElseThrow(NullPointerException::new);

		Account account = Account.builder()
				.accountNumber(accountDto.getAccountNumber())
				.password(accountDto.getPassword())
				.purpose(accountDto.getPurpose())
				.sof(accountDto.getSof())
				.user(user)
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
		User user = userRepository.findFirstByRegisterNumber(accountDto.getUserRegisterNumber()).orElseThrow(NullPointerException::new);

		Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber())
				.orElseThrow(NullPointerException::new);

		account.setAccountNumber(accountDto.getAccountNumber())
				.setPassword(accountDto.getPassword())
				.setPurpose(accountDto.getPurpose())
				.setSof(accountDto.getSof())
				.setBalance(accountDto.getBalance())
				.setUser(user);
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
				.userRegisterNumber(account.getUser().getRegisterNumber())
				.build();
		return accountDto;
	}
}
