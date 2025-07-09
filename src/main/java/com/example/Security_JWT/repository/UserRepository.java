package com.example.Security_JWT.repository;

import com.example.Security_JWT.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
}
