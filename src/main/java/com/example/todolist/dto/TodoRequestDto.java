package com.example.todolist.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TodoRequestDto {
    private String title;
    private String description;
    private LocalDate dueDate;
}
