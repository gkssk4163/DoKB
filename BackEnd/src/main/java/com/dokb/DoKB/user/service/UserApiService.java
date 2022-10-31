package com.dokb.DoKB.user.service;

import com.dokb.DoKB.common.ApiResponseStatus;
import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.domain.UserApi;
import com.dokb.DoKB.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserApiService {

    @Autowired
    private UserRepository userRepository;

    public UserApi create(UserApi request) {
        User user = User.builder()
                .registerNumber(request.getRegisterNumber())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .job(request.getJob())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);
        return response(newUser);
    }

    public UserApi read(String register_number) {
        User user = userRepository.findFirstByRegisterNumber(register_number).orElseThrow(NullPointerException::new);
        return response(user);
    }

    public UserApi update(UserApi request) {

        User user = userRepository.findFirstByRegisterNumber(request.getRegisterNumber()).orElseThrow(NullPointerException::new);
        user.setName(request.getName())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setAddress(request.getAddress())
                .setJob(request.getJob())
                .setUpdatedAt(LocalDateTime.now());
        User updateUser = userRepository.save(user);
        return response(updateUser);
    }

    public String delete(String register_number) {
        User user = userRepository.findFirstByRegisterNumber(register_number)
                .orElseThrow(NullPointerException::new);
        userRepository.delete(user);
        return ApiResponseStatus.DELETE.getLabel();
    }

    private UserApi response(User user) {
        return UserApi.builder()
                .registerNumber(user.getRegisterNumber())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(user.getAddress())
                .job(user.getJob())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
