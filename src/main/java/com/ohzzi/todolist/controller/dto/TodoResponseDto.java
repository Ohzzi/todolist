package com.ohzzi.todolist.controller.dto;

import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.user.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoResponseDto {

    private final Long id;
    private final String content;
    private final LocalDate date;
    private final Boolean isImportant;
    private final Boolean isDone;
    private final User user;

    public TodoResponseDto(Todo entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.date = entity.getDate();
        this.isImportant = entity.isImportant();
        this.isDone = entity.isDone();
        this.user = entity.getUser();
    }
}
