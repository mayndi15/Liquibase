package com.liquibase.controller;

import com.liquibase.model.User;
import com.liquibase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {

        if (service.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username should be unique.");
        }

        service.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {

        User user = service.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<User> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
