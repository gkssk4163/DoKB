package com.dokb.DoKB.history.domain;

import com.dokb.DoKB.account.domain.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
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


    //History n:1 account
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "accountNumber")
    private Account account;
}
