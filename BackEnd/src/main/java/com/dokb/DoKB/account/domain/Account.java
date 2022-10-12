package com.dokb.DoKB.account.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "account")
public class Account {
	@Id
	private String accountNumber;

	@Column(nullable = false/*, length = 100*/)
	private String password;

	@Column(nullable = false)
	private String purpose;

	@Column(nullable = false)
	private String sof;

	@ColumnDefault("0")
	private int balance;

	@Column(nullable = false)
	private Long userRegisterNumber;

	@Builder
	public Account(String accountNumber, String password, String purpose,
	               String sof, int balance, Long userRegisterNumber) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.purpose = purpose;
		this.sof = sof;
		this.balance = balance;
		this.userRegisterNumber = userRegisterNumber;
	}
}
