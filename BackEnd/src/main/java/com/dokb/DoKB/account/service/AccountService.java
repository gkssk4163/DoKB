package com.dokb.DoKB.account.service;

import com.dokb.DoKB.account.domain.Account;
import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.account.domain.AccountTransferDto;
import com.dokb.DoKB.account.repository.AccountRepository;
import com.dokb.DoKB.common.ApiResponseStatus;
import com.dokb.DoKB.history.domain.HistoryApi;
import com.dokb.DoKB.history.service.HistoryApiService;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	HistoryApiService historyService;

	public AccountDto create(AccountDto accountDto) {
		User user = userRepository.findFirstByRegisterNumber(accountDto.getUserRegisterNumber()).orElseThrow(NullPointerException::new);

		Account account = Account.builder()
				.accountNumber(createAccountNumber())
				.password(accountDto.getPassword())
				.purpose(accountDto.getPurpose())
				.sof(accountDto.getSof())
				.user(user)
				.build();
		Account newAccount = accountRepository.save(account);
		return newAccount.parseAccountDto();
	}

	private String createAccountNumber() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		long randomNum = random.nextLong();
		if (randomNum < 0) randomNum *= -1;
		String randomStr = String.valueOf(randomNum);
		return randomStr.substring(0, 6) + "-" + randomStr.substring(6, 8) + "-" + randomStr.substring(8, 14);
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

		/* 송금 및 송금이력 생성 */
		sender.setBalance(sender.getBalance() - amount);
		AccountDto afterSender = update(sender.parseAccountDto());
		HistoryApi sendHistory = HistoryApi.builder()
				.accountNumber(sender.getAccountNumber())
				.opponentAccount(receiver.getAccountNumber())
				.inOut("O")
				.amount(amount)
				.balance(afterSender.getBalance())
				.build();
		historyService.create(sendHistory);
		/* 입금 및 입금이력 생성 */
		receiver.setBalance(sender.getBalance() + amount);
		AccountDto afterReceiver = update(receiver.parseAccountDto());
		HistoryApi receiveHistory = HistoryApi.builder()
				.accountNumber(receiver.getAccountNumber())
				.opponentAccount(sender.getAccountNumber())
				.inOut("I")
				.amount(amount)
				.balance(afterReceiver.getBalance())
				.build();
		historyService.create(receiveHistory);

		return ApiResponseStatus.SUCCESS.getLabel();
	}

	public void deleteAll() {
		accountRepository.deleteAll();
	}

	public List<Account> findAllByUser(String registerNumber) {
		User user = userRepository.findFirstByRegisterNumber(registerNumber)
				.orElseThrow(NullPointerException::new);
		return user.getAccountList();
	}

	public List<Account> findDistinctOpponentAccountByAccountNumber(String accountNumber) {
		return accountRepository.findDistinctOpponentAccountByAccountNumber(accountNumber);
	}
}
