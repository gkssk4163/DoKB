package com.dokb.DoKB.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransferDto {
    private String accountNumber;

    private String opponentAccount;

    private long amount;
}
