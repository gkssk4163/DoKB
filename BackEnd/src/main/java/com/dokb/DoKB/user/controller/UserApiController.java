package com.dokb.DoKB.user.controller;

import com.dokb.DoKB.user.domain.UserApi;
import com.dokb.DoKB.user.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserApiService userApiService;

    @PostMapping("")
    public UserApi create(UserApi request) {
        return userApiService.create(request);
    }

    @GetMapping("{register_number}")
    public UserApi read(@PathVariable(name = "register_number") String register_number) {
        return userApiService.read(register_number);
    }

    @PutMapping("")
    public UserApi update(UserApi request) {
        return userApiService.update(request);
    }

    @DeleteMapping("{register_number}")
    public String delete(@PathVariable(name = "register_number") String register_number) {
        return userApiService.delete(register_number);
    }
}
