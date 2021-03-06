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
        String content = "content";

        todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(false)
                .build());

        //when
        List<Todo> todoList = todoRepository.findAll();

        //then
        Todo todo = todoList.get(0);
        assertThat(todo.getContent()).isEqualTo(content);
        assertThat(todo.getDate()).isEqualTo(date);
        assertThat(todo.isImportant()).isEqualTo(false);
        assertThat(todo.isDone()).isEqualTo(false);
    }
}