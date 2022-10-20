package com.dokb.DoKB.login;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
 //참고 사이트 https://terianp.tistory.com/m/58
@Data
@RequiredArgsConstructor
public class LoginForm {
    @NotNull
    private String accountNumber;

    @NotNull
    private String password;
}
