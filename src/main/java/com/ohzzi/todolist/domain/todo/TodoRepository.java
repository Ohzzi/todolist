package com.ohzzi.todolist.domain.todo;

import com.ohzzi.todolist.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUser(User user);
}
