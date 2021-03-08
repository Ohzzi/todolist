package com.ohzzi.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohzzi.todolist.controller.dto.TodoSaveRequestDto;
import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.todo.TodoRepository;
import com.ohzzi.todolist.domain.user.Role;
import com.ohzzi.todolist.domain.user.User;
import com.ohzzi.todolist.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    void afterEach() {
        todoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser
    @Transactional
    void todo_등록() throws Exception {
        // given
        String userEmail = "user@google.com";
        String userName = "유저";
        User user = User.builder()
                .email(userEmail)
                .name(userName)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String content = "content";
        LocalDate date = LocalDate.now();
        TodoSaveRequestDto requestDto = TodoSaveRequestDto.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .user(user)
                .build();

        String url = "http://localhost:" + port + "/api/todo";

        // when
        mvc.perform(post(url)
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        // then
        List<Todo> all = todoRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getDate()).isEqualTo(date);
        assertThat(all.get(0).isImportant()).isEqualTo(true);
        assertThat(all.get(0).isDone()).isEqualTo(false);
        assertThat(all.get(0).getUser().getName()).isEqualTo(userName);
        assertThat(all.get(0).getUser().getEmail()).isEqualTo(userEmail);
    }

    @Test
    @WithMockUser()
    @Transactional
    void Todo_조회() throws Exception {
        // given
        String userEmail = "user@google.com";
        String userName = "유저";
        User user = User.builder()
                .email(userEmail)
                .name(userName)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String content = "content";
        LocalDate date = LocalDate.now();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .user(user)
                .build());

        long id = savedTodo.getId();

        String url = "http://localhost:" + port + "/api/todo/" + id;

        // when
        MvcResult result = mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();

        // then
        assertThat(body).contains(content);
        assertThat(body).contains(date.toString());
        assertThat(body).contains("\"isImportant\":true");
        assertThat(body).contains("\"isDone\":false");
    }

    @Test
    @WithMockUser
    @Transactional
    void Todo_수정() throws Exception {
        // given
        String userEmail = "user@google.com";
        String userName = "유저";
        User user = User.builder()
                .email(userEmail)
                .name(userName)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String content = "content";
        LocalDate date = LocalDate.now();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .user(user)
                .build());

        Long id = savedTodo.getId();
        String expectedContent = "updated content";

        TodoUpdateRequestDto requestDto = TodoUpdateRequestDto.builder()
                .content(expectedContent)
                .isImportant(false)
                .isDone(true)
                .build();

        String url = "http://localhost:" + port + "/api/todo/" + id;

        // when
        mvc.perform(put(url)
                .content(new ObjectMapper().writeValueAsString(requestDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        Todo updatedTodo = todoRepository.findById(id).orElseGet(() -> fail(""));

        assertThat(updatedTodo.getContent()).isEqualTo(expectedContent);
        assertThat(updatedTodo.isImportant()).isEqualTo(false);
        assertThat(updatedTodo.isDone()).isEqualTo(true);
        assertThat(updatedTodo.getUser().getName()).isEqualTo(userName);
        assertThat(updatedTodo.getUser().getEmail()).isEqualTo(userEmail);
    }

    @Test
    @WithMockUser
    void Todo_삭제() throws Exception {
        // given
        String userEmail = "user@google.com";
        String userName = "유저";
        User user = User.builder()
                .email(userEmail)
                .name(userName)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String content = "content";
        LocalDate date = LocalDate.now();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .user(user)
                .build());

        long id = savedTodo.getId();
        String url = "http://localhost:" + port + "/api/todo/" + id;

        // when
        mvc.perform(delete(url))
                .andExpect(status().isOk());

        // then
        List<Todo> all = todoRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    @WithMockUser
    void User의_Todo_모두_찾기() throws Exception {
        // given
        String userEmail = "user@google.com";
        String userName = "유저";
        User user = User.builder()
                .email(userEmail)
                .name(userName)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        User anotherUser = User.builder()
                .email("user@naver.com")
                .name(userName)
                .role(Role.USER)
                .build();
        userRepository.save(anotherUser);

        String content = "content";
        LocalDate date = LocalDate.now();
        String dateString = date.toString();
        Todo savedTodo = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .user(user)
                .build());

        Todo savedTodo2 = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(false)
                .user(user)
                .build());

        Todo savedTodo3 = todoRepository.save(Todo.builder()
                .content(content)
                .date(date)
                .isImportant(false)
                .user(anotherUser)
                .build());

        String url = "http://localhost:" + port + "/api/todos/" + userEmail + "/" + dateString;

        // when
        MvcResult result = mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();

        // then
        System.out.println(result.getResponse());
        System.out.println(body);
        assertThat(body).contains(content);
        assertThat(body).contains(date.toString());
        assertThat(body).contains("\"isImportant\":true");
        assertThat(body).contains("\"isDone\":false");
    }
}