package com.ohzzi.todolist.domain.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @AfterEach
    void cleanUp() {
        todoRepository.deleteAll();
    }

    @Test
    void Todo_저장_불러오기() {
        //given
        LocalDate date = LocalDate.now();
        String title = "title";

        todoRepository.save(Todo.builder()
                .title(title)
                .date(date)
                .isImportant(false)
                .build());

        //when
        List<Todo> todoList = todoRepository.findAll();

        //then
        Todo todo = todoList.get(0);
        assertThat(todo.getTitle()).isEqualTo(title);
        assertThat(todo.getDate()).isEqualTo(date);
        assertThat(todo.isImportant()).isEqualTo(false);
        assertThat(todo.isActivated()).isEqualTo(false);
    }
}