package com.dokb.DoKB.repository;

import com.dokb.DoKB.DoKbApplicationTests;
import com.dokb.DoKB.user.User;
import com.dokb.DoKB.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends DoKbApplicationTests {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void create(){
//        Integer registerNumber = 22222222;
//        String name = "test1";
//        String phoneNumber = "010-111-111";
//        String email = "test@test.com";
//        String address = "서울시 어딘가";
//        String job ="백수";
//        LocalDateTime createdAt = LocalDateTime.now();
//        LocalDateTime updatedAt = LocalDateTime.now();
//
//        User user = User.builder().
//                registerNumber(registerNumber).
//                name(name).
//                phoneNumber(phoneNumber).
//                email(email).
//                address(address).
//                job(job).build();
////                createdAt(createdAt).
////                updatedAt(updatedAt).build();
//
//        User newUser = userRepository.save(user);
//        Assertions.assertNotNull(newUser);
//    }
}