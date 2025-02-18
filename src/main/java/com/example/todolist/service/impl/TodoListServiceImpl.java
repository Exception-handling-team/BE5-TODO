package com.example.todolist.service.impl;

import com.example.todolist.entity.Todo;
import com.example.todolist.entity.User;
import com.example.todolist.repository.TodoListRepository;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    /*public TodoListServiceImpl(TodoListRepository todoListRepository, UserRepository userRepository) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }*/

    @Override
    @Transactional
    public void addTodo(String title, String description, String dueDate) {

        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));


        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDueDate(LocalDate.parse(dueDate));
        todo.setStatus("PENDING");
        todo.setCreatedBy(user);
        todoListRepository.save(todo);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
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
