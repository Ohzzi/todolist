package com.ohzzi.todolist.domain.todo;

import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30, nullable = false)
    private String content;
    private LocalDate date; // 목표 날짜
    private boolean isImportant; // 중요 메모인지 확인
    private boolean isActivated = true; // todo의 이행 여부
    @ManyToOne
    private User user;

    @Builder
    public Todo(long id, String content, LocalDate date, boolean isImportant, boolean isActivated, User user) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.isImportant = isImportant;
        this.isActivated = isActivated;
        this.user = user;
    }

    public void update(TodoUpdateRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.isImportant = requestDto.getIsImportant();
        this.isActivated = requestDto.getIsActivated();
    }
}
