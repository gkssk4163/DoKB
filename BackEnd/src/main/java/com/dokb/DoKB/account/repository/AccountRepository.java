package com.dokb.DoKB.account.repository;

import com.dokb.DoKB.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByAccountNumber(String accountNumber);

	@Query(value =
			"SELECT * " +
			"FROM dokb.account " +
			"WHERE account_number in (" +
			"   SELECT distinct(opponent_account)" +
			"   FROM dokb.history" +
			"   WHERE account_number=?1)",
			nativeQuery = true)
	List<Account> findDistinctOpponentAccountByAccountNumber(String accountNumber);
}
