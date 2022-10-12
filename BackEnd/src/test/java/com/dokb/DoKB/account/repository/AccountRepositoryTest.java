package com.dokb.DoKB.account.repository;

import com.dokb.DoKB.account.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {
	@Autowired
	AccountRepository accountRepository;

	@Test
	public void insertAccount() {
		Account account = Account.builder()
				.accountNumber("123-45-67890")
				.password("1234")
				.purpose("월급통장")
				.sof("월급")
				.balance(0)
				.userRegisterNumber(9507292222222L)
				.build();

		accountRepository.save(account);
	}
}