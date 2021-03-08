package com.ohzzi.todolist.controller.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TodoUpdateRequestDto {

    private String content;
    private Boolean isImportant;
    private Boolean isDone;

}
