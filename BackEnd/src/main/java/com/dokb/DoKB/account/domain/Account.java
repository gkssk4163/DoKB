package com.dokb.DoKB.account.domain;

import com.dokb.DoKB.history.domain.History;
import com.dokb.DoKB.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity(name = "account")
public class Account {
    @Id
    private String accountNumber;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 45)
    private String purpose;

    @Column(nullable = false, length = 45)
    private String sof;

    @ColumnDefault("0")
    private long balance;

    // Account n:1 user
    @ManyToOne
    @JoinColumn(name = "userRegisterNumber")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<History> historyList;

    public AccountDto parseAccountDto() {
        return AccountDto.builder()
                .accountNumber(this.getAccountNumber())
                .balance(this.getBalance())
                .password(this.getPassword())
                .purpose(this.getPurpose())
                .sof(this.getSof())
                .userRegisterNumber(this.getUser().getRegisterNumber())
                .build();
    }
}
