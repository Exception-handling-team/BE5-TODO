package com.example.todolist.service.impl;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoListRepository;
import com.example.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository = new TodoListRepository();

    @Override
    public void addTodo(Scanner scanner) {
        System.out.print("할 일 제목: ");
        String title = scanner.nextLine();
        System.out.print("할 일 설명: ");
        String description = scanner.nextLine();
        System.out.print("마감일 (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDueDate(LocalDate.parse(dueDate));
        todo.setStatus("PENDING");

        todoListRepository.save(todo);
        System.out.println("새로운 할 일이 추가되었습니다: " + todo);
    }

    @Override
    public void listTodos() {
        List<Todo> todos = todoListRepository.findAll();
        if (todos.isEmpty()) {
            System.out.println("할 일이 없습니다.");
        } else {
            todos.forEach(System.out::println);
        }
    }

    @Override
    public void updateTodoStatus(Scanner scanner) {
        System.out.print("상태를 변경할 할 일 ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // 개행 문자 제거
        System.out.print("새 상태 (PENDING/COMPLETED): ");
        String status = scanner.nextLine();

        Todo todo = todoListRepository.findById(id);
        if (todo != null) {
            todo.setStatus(status);
            todoListRepository.save(todo);
            System.out.println("상태가 변경되었습니다: " + todo);
        } else {
            System.out.println("할 일을 찾을 수 없습니다.");
        }
    }

    @Override
    public void deleteTodo(Scanner scanner) {
        System.out.print("삭제할 할 일 ID: ");
        Long id = scanner.nextLong();

        Todo todo = todoListRepository.findById(id);
        if (todo != null) {
            todoListRepository.delete(todo);
            System.out.println("할 일이 삭제되었습니다.");
        } else {
            System.out.println("할 일을 찾을 수 없습니다.");
        }
    }

    /**
     * PENDING 상태의 할 일 개수를 반환.
     */
    @Override
    public long countPendingTodos() {
        List<Todo> todos = todoListRepository.findAll();
        return todos.stream()
                .filter(todo -> "PENDING".equalsIgnoreCase(todo.getStatus()))
                .count();
    }

}
