package com.liquibase.service;

import com.liquibase.model.Role;
import com.liquibase.model.User;
import com.liquibase.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    public User create(User user) {
        user.setRole(Role.ROLE_USER);
        user.setCreateDate(LocalDateTime.now());
        return repository.save(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
