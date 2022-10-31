package com.dokb.DoKB.login;


import com.dokb.DoKB.account.domain.AccountDto;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.domain.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    SessionManager sessionManager;
    @PostMapping("/login")
    public UserApi login(LoginForm loginForm, HttpServletResponse response,HttpServletRequest request){
        User loginUser = loginService.login(loginForm.getAccountNumber(), loginForm.getPassword());


        sessionManager.createSession(loginUser, response);
        return loginUser.parseUserApi();
    };

    @PostMapping("/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){
        Cookie registerCookie =new Cookie("registerNumber",null);
        registerCookie.setMaxAge(0);
        response.addCookie(registerCookie);
        Cookie nameCookie =new Cookie("name",null);
        nameCookie.setMaxAge(0);
        response.addCookie(nameCookie);
        Cookie sessionCookie =new Cookie("session",null);
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
        sessionManager.expireCookie(request);
        System.out.println("Sss");
    }
}
