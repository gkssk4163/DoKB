package com.dokb.DoKB.user.service.repository;

import com.dokb.DoKB.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findFirstByRegisterNumber(String registerNumber);
}