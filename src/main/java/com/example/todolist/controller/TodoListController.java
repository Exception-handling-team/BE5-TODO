package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@Tag(name = "TodoList API", description = "할 일 목록 관리 API")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "할 일 추가", description = "새로운 할 일을 추가합니다.")
    public String addTodo(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String dueDate) {
        todoListService.addTodo(title, description, dueDate);
        return "할 일이 추가되었습니다.";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "모든 할 일 조회", description = "등록된 모든 할 일 목록을 반환합니다.")
    public List<Todo> listTodos() {
        return todoListService.listTodos();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    @Operation(summary = "할 일 상태 변경", description = "할 일의 상태(PENDING/COMPLETED)를 변경합니다.")
    public String updateTodoStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        todoListService.updateTodoStatus(id, status);
        return "할 일 상태가 변경되었습니다.";
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "할 일 삭제", description = "할 일을 삭제합니다.")
    public String deleteTodo(@PathVariable Long id) {
        todoListService.deleteTodo(id);
        return "할 일이 삭제되었습니다.";
    }
}
