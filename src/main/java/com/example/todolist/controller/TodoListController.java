package com.example.todolist.controller;

import com.example.todolist.service.TodoListService;
import com.example.todolist.service.impl.TodoListServiceImpl;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class TodoListController {

    private final TodoListService todoListService = new TodoListServiceImpl() {};

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- To-Do List APP ---");
            System.out.println("1. 할 일 추가");
            System.out.println("2. 할 일 목록 조회");
            System.out.println("3. 할 일 상태 변경");
            System.out.println("4. 할 일 삭제");
            System.out.println("5. 종료");
            System.out.print("메뉴 선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> todoListService.addTodo(scanner);
                case 2 -> todoListService.listTodos();
                case 3 -> todoListService.updateTodoStatus(scanner);
                case 4 -> todoListService.deleteTodo(scanner);
                case 5 -> {
                    System.out.println("종료.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
}
