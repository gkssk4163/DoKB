package com.dokb.DoKB.openAccount;

import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.domain.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openAccount")
public class OpenAccountController {

    @Autowired
    OpenAccountService openAccountService;
    @PostMapping
    public AccountDto create (UserApi userRequest, AccountDto accountRequest){

        return openAccountService.create(userRequest,accountRequest);}

}
