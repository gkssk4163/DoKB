package com.dokb.DoKB.user;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class User{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String registerNumber;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String job;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
