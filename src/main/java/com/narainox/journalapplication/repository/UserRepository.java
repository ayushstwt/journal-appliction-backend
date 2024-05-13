package com.narainox.journalapplication.repository;

import com.narainox.journalapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
