package com.example.jira.service;

import com.example.jira.model.User;
import com.example.jira.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        try {
            if (userRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent()) {
                throw new RuntimeException("Username already taken!");
            }

            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user: " + e.getMessage());
        }
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
