package com.dokb.DoKB.account.service;

import com.dokb.DoKB.account.domain.AccountDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    AccountDto accountDto;

    @BeforeEach
    void setUp() {
        accountDto = AccountDto.builder()
                .password("1234")
                .purpose("목적")
                .sof("자금출처")
                .userRegisterNumber("string")
                .build();
    }

    @RepeatedTest(20)
    public void createAccountNumberTest() {
        AccountDto account = accountService.create(accountDto);
        System.out.println(account.getAccountNumber());
        assertEquals(16, account.getAccountNumber().length());
    }

    @AfterEach
    void tearDown() {
        accountService.deleteAll();
    }
}