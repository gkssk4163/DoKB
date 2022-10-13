package com.dokb.DoKB.account.repository;

import com.dokb.DoKB.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByAccountNumber(String accountNumber);
}
