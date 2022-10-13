package com.dokb.DoKB.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

	@Column(nullable = false)
	private String userRegisterNumber;
}
