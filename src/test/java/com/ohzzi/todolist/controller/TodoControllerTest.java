package com.ohzzi.todolist.controller;

import com.ohzzi.todolist.controller.dto.TodoSaveRequestDto;
import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.todo.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

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
        String content = "content";
        LocalDate date = LocalDate.now();
        TodoSaveRequestDto requestDto = TodoSaveRequestDto.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .build();

        String url = "http://localhost:" + port + "/api/todo";

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Todo> all = todoRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getDate()).isEqualTo(date);
        assertThat(all.get(0).isImportant()).isEqualTo(true);
        assertThat(all.get(0).isActivated()).isEqualTo(false);
    }

    @Test
    void Todo_조회() {
        // given
        String content = "content";
        LocalDate date = LocalDate.now();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .build());

        long id = savedTodo.getId();

        // when
        String url = "http://localhost:" + port + "/api/todo/" + id;

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        String body = responseEntity.getBody();

        assertThat(body).contains(content);
        assertThat(body).contains(date.toString());
        assertThat(body).contains("\"isImportant\":true");
        assertThat(body).contains("\"isActivated\":false");
    }

    @Test
    void Todo_수정() {
        // given
        String content = "content";
        LocalDate date = LocalDate.now();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .build());

        Long id = savedTodo.getId();
        String expectedContent = "updated content";

        TodoUpdateRequestDto requestDto = TodoUpdateRequestDto.builder()
                .content(expectedContent)
                .isImportant(false)
                .isActivated(true)
                .build();

        HttpEntity<TodoUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:" + port + "/api/todo/" + id;

        // when
        ResponseEntity<Long> responseEntity
                = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Todo updatedTodo = todoRepository.findById(id).orElseGet(() -> fail(""));

        assertThat(updatedTodo.getContent()).isEqualTo(expectedContent);
        assertThat(updatedTodo.isImportant()).isEqualTo(false);
        assertThat(updatedTodo.isActivated()).isEqualTo(true);

    }

    @Test
    void Todo_삭제() {
        // given
        String content = "content";
        LocalDate date = LocalDate.now();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .build());

        long id = savedTodo.getId();
        String url = "http://localhost:" + port + "/api/todo/" + id;

        // when
        testRestTemplate.delete(url);

        // then

        List<Todo> all = todoRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }
}