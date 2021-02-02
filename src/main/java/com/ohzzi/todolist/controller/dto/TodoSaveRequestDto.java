package com.ohzzi.todolist.controller.dto;

import com.ohzzi.todolist.domain.todo.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoSaveRequestDto {

    private String title;
    private LocalDate date;
    private Boolean isImportant;

    @Builder
    public TodoSaveRequestDto(String title, LocalDate date, Boolean isImportant) {
        this.title = title;
        this.date = date;
        this.isImportant = isImportant;
    }

    public Todo ToEntity() {
        return Todo.builder()
                .title(title)
                .date(date)
                .isImportant(isImportant)
                .build();
    }
}
