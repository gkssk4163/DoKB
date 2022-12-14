package com.dokb.DoKB.user.domain;


import com.dokb.DoKB.account.domain.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Account> accountList;

    public UserApi parseUserApi(){
        return UserApi.builder()
                .registerNumber(this.getRegisterNumber())
                .name(this.getName())
                .phoneNumber(this.getPhoneNumber())
                .email(this.getEmail())
                .address(this.getAddress())
                .address(this.getAddress())
                .job(this.getJob())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
