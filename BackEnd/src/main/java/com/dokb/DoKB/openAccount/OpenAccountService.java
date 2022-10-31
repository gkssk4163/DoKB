package com.dokb.DoKB.openAccount;

import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.account.service.AccountService;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.domain.UserApi;
import com.dokb.DoKB.user.service.UserApiService;
import com.dokb.DoKB.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class OpenAccountService {

	@Autowired
	UserApiService userApiService;

	@Autowired
	AccountService accountService;
	@Autowired
	UserRepository userRepository;

	public AccountDto create(UserApi userRequest, AccountDto accountRequest) {
		String registerNumber = userRequest.getRegisterNumber();
		User user = userRepository.findFirstByRegisterNumber(registerNumber)
				.orElseGet(new Supplier<User>() {
					@Override
					public User get() {
						UserApi userApi = userApiService.create(userRequest);
						return userRepository
								.findFirstByRegisterNumber(userApi.getRegisterNumber())
								.orElseThrow(NullPointerException::new);
					}
				});

		accountRequest.setUserRegisterNumber(user.getRegisterNumber());
		return accountService.create(accountRequest);
	}
}
