package com.example.todolist.repository;

import com.example.todolist.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoListRepository {
    private final List<Todo> todos = new ArrayList<>();
    private Long nextId = 1L;

    public void save(Todo todo) {
        if (todo.getId() == null) {
            todo.setId(nextId++);
            todos.add(todo);
        } else {
            // 기존 데이터 업데이트
            todos.removeIf(t -> t.getId().equals(todo.getId()));
            todos.add(todo);
        }
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    public Todo findById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(Todo todo) {
        todos.removeIf(t -> t.getId().equals(todo.getId()));
    }

}
