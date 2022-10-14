package com.dokb.DoKB.history.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryApi {

    private Long id;

    private String opponentAccount;

    private LocalDateTime dealDate;

    private String inOut;

    private BigDecimal amount;

    private BigDecimal balance;

    private String accountNumber;
}
