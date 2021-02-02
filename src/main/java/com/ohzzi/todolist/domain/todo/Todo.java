package com.ohzzi.todolist.domain.todo;

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
    private String title;
    private LocalDate date; // 목표 날짜
    private boolean isImportant; // 중요 메모인지 확인
    private boolean isActivated = true; // todo의 이행 여부

    @Builder
    public Todo(long id, String title, LocalDate date, boolean isImportant, boolean isActivated) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.isImportant = isImportant;
        this.isActivated = isActivated;
    }
}
