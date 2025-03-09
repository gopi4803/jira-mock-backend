package com.example.jira.controller;

import com.example.jira.model.User;
import com.example.jira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // HTTP 200 OK
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // HTTP 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // HTTP 400 Bad Request
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllUsers(){
        userService.deleteAllUsers();
        return ResponseEntity.ok("User deleted successfully");
    }
}
