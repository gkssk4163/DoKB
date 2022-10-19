package com.dokb.DoKB.user.domain;


import com.dokb.DoKB.account.domain.Account;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ToString(exclude = {"account", "history"})
public class User {

    @Id
    private String registerNumber;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String job;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Account> accountList;
}
