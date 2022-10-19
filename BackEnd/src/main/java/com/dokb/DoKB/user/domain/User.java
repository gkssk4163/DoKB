package com.dokb.DoKB.user.domain;


import com.dokb.DoKB.account.domain.Account;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString()
@Accessors(chain = true)
public class User{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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
