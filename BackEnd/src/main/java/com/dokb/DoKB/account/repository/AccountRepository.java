package com.dokb.DoKB.account.repository;

import com.dokb.DoKB.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
