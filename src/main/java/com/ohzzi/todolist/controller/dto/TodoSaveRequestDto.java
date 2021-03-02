package com.ohzzi.todolist.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoSaveRequestDto {

    private String content;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate date;
    private Boolean isImportant;
    private User user;

    @Builder
    public TodoSaveRequestDto(String content, LocalDate date, Boolean isImportant, User user) {
        this.content = content;
        this.date = date;
        this.isImportant = isImportant;
        this.user = user;
    }

    public Todo ToEntity() {
        return Todo.builder()
                .content(content)
                .date(date)
                .isImportant(isImportant)
                .user(user)
                .build();
    }
}
