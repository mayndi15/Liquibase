package com.liquibase.repository;

import com.liquibase.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUserIdAndDoneFalse(Long userId);
}
