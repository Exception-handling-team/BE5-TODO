package com.example.todolist.util;

import com.example.todolist.service.TodoListService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TodoScheduler {

    private final TodoListService todoService;

    public TodoScheduler(TodoListService todoService) {
        this.todoService = todoService;
    }

    /**
     * 매일 오전 9시에 남은 할 일의 개수를 출력.
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void reportPendingTodos() {
        String message = generatePendingTodosMessage();
        System.out.println(message);
    }

    // 테스트 코드를 짜기 위한 메서드 분리
    public String generatePendingTodosMessage() {
        long pendingCount = todoService.countPendingTodos();
        return "[스케줄러] 현재 남은 할 일은 " + pendingCount + "개입니다.";
    }
}