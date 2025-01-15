package com.example.todolist.util;

import com.example.todolist.controller.TodoListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoRunner implements CommandLineRunner {

    private TodoListController todoListController;

    public TodoRunner(TodoListController todoListController) {
        this.todoListController = todoListController;
    }

    @Override
    public void run(String... args) throws Exception {
        if (isTestEnvironment()) {
            return; // 테스트 환경에서는 실행 X
        }
        todoListController.start(); // start 메서드 실행
    }

    private boolean isTestEnvironment() {
        return System.getProperty("test.environment") != null;
    }
}
