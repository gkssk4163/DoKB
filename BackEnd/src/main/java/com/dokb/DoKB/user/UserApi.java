package com.dokb.DoKB.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApi {
    private String registerNumber;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String job;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
