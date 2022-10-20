package com.dokb.DoKB.history.domain;

import com.dokb.DoKB.account.domain.Account;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString(exclude = {"account"})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String opponentAccount;

    private LocalDateTime dealDate;

    private String inOut;

    private BigDecimal amount;

    private BigDecimal balance;


    //Histort n:1 account
    @ManyToOne
    @JoinColumn(name = "accountNumber")
    private Account account;

    public HistoryApi parseHistoryApi(){
        return HistoryApi.builder()
                .id(this.getId())
                .opponentAccount(this.getOpponentAccount())
                .dealDate(this.getDealDate())
                .inOut(this.getInOut())
                .amount(this.getAmount())
                .balance(this.getBalance())
                .accountNumber(this.getOpponentAccount())
                .build();
    }
}
