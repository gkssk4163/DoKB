package com.dokb.DoKB.account.repository;

import com.dokb.DoKB.account.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountRepositoryTest {
//	@Autowired
//	AccountRepository accountRepository;
//
//	@Test
//	public void findByAccountNumber() {
//		Account account = Account.builder()
//				.accountNumber("123-45-67890")
//				.password("1234")
//				.purpose("월급통장")
//				.sof("월급")
//				.userRegisterNumber("950729-2222222")
//				.build();
//
//		accountRepository.save(account);
//
//		Account result = accountRepository.findByAccountNumber("123-45-67890").get();
//		assertEquals(account, result);
//	}
//
//	@AfterEach
//	void tearDown() {
//		accountRepository.deleteAll();
//	}
}