package com.dokb.DoKB.login;

import com.dokb.DoKB.account.domain.Account;
import com.dokb.DoKB.account.repository.AccountRepository;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.domain.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AccountRepository accountRepository;
    public User login(String accountNumber, String password){
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(NullPointerException::new);

        if(! account.getPassword().equals(password) ){
            throw new NullPointerException();
        }
        else{
            //세션생성
        };
        User user = account.getUser();
        return user;
    };
};
