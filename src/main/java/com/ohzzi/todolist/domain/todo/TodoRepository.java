package com.ohzzi.todolist.domain.todo;

import com.ohzzi.todolist.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserAndDate(User user, LocalDate date);
}
