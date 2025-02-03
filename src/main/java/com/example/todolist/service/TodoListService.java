package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import java.util.List;

public interface TodoListService {
    void addTodo(String title, String description, String dueDate);
    List<Todo> listTodos();
    void updateTodoStatus(Long id, String status);
    void deleteTodo(Long id);
    long countPendingTodos();
}