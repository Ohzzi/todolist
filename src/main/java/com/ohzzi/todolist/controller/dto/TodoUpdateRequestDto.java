package com.ohzzi.todolist.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoUpdateRequestDto {

    private String content;
    private Boolean isImportant;
    private Boolean isActivated;

    @Builder
    public TodoUpdateRequestDto(String content, Boolean isImportant, Boolean isActivated) {
        this.content = content;
        this.isImportant = isImportant;
        this.isActivated = isActivated;
    }
}
