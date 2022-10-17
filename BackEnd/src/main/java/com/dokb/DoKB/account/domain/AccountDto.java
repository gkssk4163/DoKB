package com.dokb.DoKB.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private String accountNumber;

	private String password;

	private String purpose;

	private String sof;

	private long balance;

	private String userRegisterNumber;
}
