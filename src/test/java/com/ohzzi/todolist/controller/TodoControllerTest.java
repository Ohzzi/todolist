package com.ohzzi.todolist.controller;

import com.ohzzi.todolist.controller.dto.TodoSaveRequestDto;
import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.todo.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void afterEach() {
        todoRepository.deleteAll();
    }

    @Test
    void todo_등록() {
        // given
        String title = "title";
        LocalDate date = LocalDate.now();
        TodoSaveRequestDto requestDto = TodoSaveRequestDto.builder()
                .title(title)
                .date(date)
                .isImportant(true)
                .build();

        String url = "http://localhost:" + port + "/api/v1/todo";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Todo> all = todoRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getDate()).isEqualTo(date);
        assertThat(all.get(0).isImportant()).isEqualTo(true);
        assertThat(all.get(0).isActivated()).isEqualTo(false);
    }
}