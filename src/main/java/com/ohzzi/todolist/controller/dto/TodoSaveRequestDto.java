package com.ohzzi.todolist.controller.dto;

import com.ohzzi.todolist.domain.todo.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoSaveRequestDto {

    private String content;
    private LocalDate date;
    private Boolean isImportant;

    @Builder
    public TodoSaveRequestDto(String content, LocalDate date, Boolean isImportant) {
        this.content = content;
        this.date = date;
        this.isImportant = isImportant;
    }

    public Todo ToEntity() {
        return Todo.builder()
                .content(content)
                .date(date)
                .isImportant(isImportant)
                .build();
    }
}
