package com.dokb.DoKB.user.service;

import com.dokb.DoKB.user.domain.User;
import com.dokb.DoKB.user.domain.UserApi;
import com.dokb.DoKB.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class UserApiService {

    @Autowired
    private UserRepository userRepository;

    public UserApi create(UserApi request){
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
        User newUser =userRepository.save(user);
        return response(newUser);
    }

    public Optional<UserApi> read(String register_number){
        Optional<User> optional = Optional.ofNullable(userRepository.findFirstByRegisterNumber(register_number));
        return optional.map(user->response(user));
    }

    public Optional<UserApi> update(UserApi request){


        Optional<User> optional = Optional.ofNullable(userRepository.findFirstByRegisterNumber(request.getRegisterNumber()));

        return optional.map(user->{
                    user.setName(request.getName())
                            .setPhoneNumber(request.getPhoneNumber())
                            .setEmail(request.getEmail())
                            .setAddress(request.getAddress())
                            .setJob(request.getJob())
                            .setUpdatedAt(LocalDateTime.now());
                    return user;
                })
                .map(user->userRepository.save(user))
                .map(updateUser->response(updateUser));
    }

    public String delete(String register_number){
        Optional<User> optional = Optional.ofNullable(userRepository.findFirstByRegisterNumber(register_number));

        return optional.map(user -> {
            userRepository.delete(user);
            return "Delete";
        }).orElseGet(()->"nodata");
    }

    private UserApi response(User user){
        UserApi userApi = UserApi.builder()
                .registerNumber(user.getRegisterNumber())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(user.getAddress())
                .job(user.getJob())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
        return userApi;
    }
}
