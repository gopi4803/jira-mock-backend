package com.example.jira.repository;

import com.example.jira.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsernameIgnoreCase(String username);
}
