package com.web.repository;

import com.web.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository로 Spring Data JPA 활용
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
