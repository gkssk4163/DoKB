package com.dokb.DoKB.history.repository;

import com.dokb.DoKB.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value =
            "SELECT * FROM dokb.history " +
            "WHERE account_number=?1 " +
            "AND year(deal_date) = ?2 " +
            "AND month(deal_date) = ?3",
    nativeQuery = true)
    List<History> findHistoryList(String accountNumber, Integer year, Integer month);
}
