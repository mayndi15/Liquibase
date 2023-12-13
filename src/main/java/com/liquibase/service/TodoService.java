package com.liquibase.service;

import com.liquibase.model.TodoItem;
import com.liquibase.model.User;
import com.liquibase.repository.ITodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private ITodoItemRepository repository;

    @Autowired
    private UserService userService;

    public TodoItem save(TodoItem todoItem, String username) {
        User user = userService.findByUsername(username);

        todoItem.setUserId(user.getId());
        todoItem.setCreateDate(LocalDateTime.now());
        todoItem.setDone(false);
        todoItem.setUpdateDate(LocalDateTime.now());
        return repository.save(todoItem);
    }

    public TodoItem completeTask(Long itemId) {
        TodoItem todoItem = repository.findById(itemId).orElse(null);

        todoItem.setUpdateDate(LocalDateTime.now());
        todoItem.setDone(true);
        return repository.save(todoItem);
    }

    public List<TodoItem> findWaitingList(Long userId) {
        return repository.findByUserIdAndDoneFalse(userId).stream().toList();
    }
}
