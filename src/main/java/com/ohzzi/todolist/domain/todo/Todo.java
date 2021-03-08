package com.ohzzi.todolist.domain.todo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private long id;
    @Column(length = 30, nullable = false)
    private String content;
    private LocalDate date; // 목표 날짜
    private boolean isImportant; // 중요 메모인지 확인
    private boolean isDone = false; // todo의 이행 여부
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void update(TodoUpdateRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.isImportant = requestDto.getIsImportant();
        this.isDone = requestDto.getIsDone();
    }

    //==연관관계 편의 메소드==//
    public void addTodoToUser(User user) {
        this.user = user;
        user.getTodos().add(this);
    }

}
