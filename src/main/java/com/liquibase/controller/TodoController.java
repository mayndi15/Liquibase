package com.liquibase.controller;

import com.liquibase.model.TodoItem;
import com.liquibase.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping("{username}")
    public ResponseEntity<?> createTodo(@PathVariable String username, @RequestBody TodoItem todoItem) {
        return ResponseEntity.ok(service.save(todoItem, username));
    }

    @PutMapping("{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(service.completeTask(todoId));
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getWaitingTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findWaitingList(userId));
    }
}
