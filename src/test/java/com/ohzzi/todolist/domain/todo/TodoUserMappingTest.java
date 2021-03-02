package com.ohzzi.todolist.domain.todo;

import com.ohzzi.todolist.domain.user.Role;
import com.ohzzi.todolist.domain.user.User;
import com.ohzzi.todolist.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TodoUserMappingTest {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanUp() {
        todoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    void Todo_User_매핑() {
        //given
        User user = User.builder()
                .email("test@test.com")
                .name("테스트")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String content = "content";
        LocalDate date = LocalDate.now();
        Todo todo = Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .build();
        todoRepository.save(todo);
        todo.addTodoToUser(user);

        //when
        Todo todoResult = todoRepository.findAll().get(0);

        //then
        assertThat(todoResult.getContent()).isEqualTo(content);
        assertThat(todoResult.getUser().getName()).isEqualTo(user.getName());
        assertThat(todoResult.getUser().getTodos().get(0)).isEqualTo(todo);

    }

    @Test
    @Transactional
    void User_삭제하면_Todo_삭제() {
        //given
        User user = User.builder()
                .email("test@test.com")
                .name("테스트")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        String content = "content";
        LocalDate date = LocalDate.now();
        Todo todo = Todo.builder()
                .content(content)
                .date(date)
                .isImportant(true)
                .build();
        todoRepository.save(todo);
        todo.addTodoToUser(user);

        //when
        userRepository.deleteAll();

        //then
        List<User> userList = userRepository.findAll();
        List<Todo> todoList = todoRepository.findAll();
        assertThat(userList.size()).isEqualTo(0);
        assertThat(todoList.size()).isEqualTo(0);

    }
}
