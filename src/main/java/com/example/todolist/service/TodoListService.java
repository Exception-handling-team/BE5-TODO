package com.example.todolist.service;

import java.util.Scanner;

public interface TodoListService {

    void addTodo(Scanner sc);
    void listTodos();
    void updateTodoStatus(Scanner sc);
    void deleteTodo(Scanner sc);
    long countPendingTodos();
}
