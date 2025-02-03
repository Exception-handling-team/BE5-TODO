package com.example.todolist.service.impl;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoListRepository;
import com.example.todolist.service.TodoListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    @Transactional
    public void addTodo(String title, String description, String dueDate) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDueDate(LocalDate.parse(dueDate));
        todo.setStatus("PENDING");
        todoListRepository.save(todo);
    }

    @Override
    public List<Todo> listTodos() {
        return todoListRepository.findAll();
    }

    @Override
    @Transactional
    public void updateTodoStatus(Long id, String status) {
        Optional<Todo> todoOpt = todoListRepository.findById(id);
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();
            todo.setStatus(status);
            todoListRepository.save(todo);
        }
    }

    @Override
    @Transactional
    public void deleteTodo(Long id) {
        todoListRepository.deleteById(id);
    }

    @Override
    public long countPendingTodos() {
        return todoListRepository.findByStatus("PENDING").size();
    }
}
