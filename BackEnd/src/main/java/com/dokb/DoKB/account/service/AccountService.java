package com.dokb.DoKB.account.service;

import com.dokb.DoKB.account.domain.Account;
import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.account.domain.AccountTransferDto;
import com.dokb.DoKB.account.repository.AccountRepository;
import com.dokb.DoKB.common.ApiResponseStatus;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
		return newAccount.parseAccountDto();
	}

	public AccountDto read(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(NullPointerException::new);
		return account.parseAccountDto();
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

		return updateAccount.parseAccountDto();
	}

	public String delete(String accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(NullPointerException::new);

		accountRepository.delete(account);

		return ApiResponseStatus.DELETE.getLabel();
	}

	@Transactional
	public String transfer(AccountTransferDto accountTransferDto) {
		Account sender = accountRepository.findByAccountNumber(accountTransferDto.getAccountNumber())
				.orElseThrow(NullPointerException::new);
		Account receiver = accountRepository.findByAccountNumber(accountTransferDto.getOpponentAccount())
				.orElseThrow(NullPointerException::new);
		long amount = accountTransferDto.getAmount();

		if (sender.getBalance() < amount) {
			throw new IllegalArgumentException("이체가능한 금액을 초과하였습니다.");
		}
		sender.setBalance(sender.getBalance() - amount);
		update(sender.parseAccountDto());
		receiver.setBalance(sender.getBalance() + amount);
		update(receiver.parseAccountDto());

		return ApiResponseStatus.SUCCESS.getLabel();
	}
}
