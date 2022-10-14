package com.dokb.DoKB.user.repository;

import com.dokb.DoKB.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findFirstByRegisterNumber(String registerNumber);
}