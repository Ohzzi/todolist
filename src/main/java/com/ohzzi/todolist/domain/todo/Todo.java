package com.ohzzi.todolist.domain.todo;

import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private long id;
    @Column(length = 30, nullable = false)
    private String content;
    private LocalDate date; // 목표 날짜
    private boolean isImportant; // 중요 메모인지 확인
    private boolean isActivated = true; // todo의 이행 여부
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
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

    //==연관관계 편의 메소드==//
    public void addTodoToUser(User user) {
        this.user = user;
        user.getTodos().add(this);
    }

}
