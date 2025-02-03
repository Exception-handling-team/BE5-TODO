package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoListRepository extends JpaRepository<Todo, Long> {

    // 스케줄러 카운트
    List<Todo> findByStatus(String status);
}