package com.example.todolist;

import com.example.standard.TestBot;
import com.example.todolist.service.TodoListService;
import com.example.todolist.util.TodoScheduler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TodolistApplicationTests {

    @InjectMocks
    private TodoScheduler todoScheduler;

    @Mock
    private TodoListService todoService;

    static {
        // 테스트 환경 속성 설정
        System.setProperty("test.environment", "true");
    }

    @Test
    @DisplayName("TODO APP - 스케줄러 테스트")
    public void testPendingTodosMessage() {

        // Given
        Mockito.when(todoService.countPendingTodos()).thenReturn(5L); // 할 일이 5개가 남았다.

        // When
        String message = todoScheduler.generatePendingTodosMessage();

        // Then
        assertEquals("[스케줄러] 현재 남은 할 일은 5개입니다.", message);

        System.out.println("테스트 메시지: " + message);

        // Mock
        Mockito.verify(todoService, Mockito.times(1)).countPendingTodos();
    }

    /*@Test
    @DisplayName("종료테스트")
    void breakTest(){
        String out = TestBot.run("");

        assertThat(out)
                .contains("명령 )")
                .contains("TODO앱을 종료합니다.");
    }*/

    /*@Test
    @DisplayName("추가 - TodoList 할일 추가")
    void todoCreateTest(){

    }*/
}
