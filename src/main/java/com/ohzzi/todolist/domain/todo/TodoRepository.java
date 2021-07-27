package com.ohzzi.todolist.domain.todo;

import com.ohzzi.todolist.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * user 와 date 정보를 가지고 조건에 맞는 할 일들을 리스트로 가져오는 메소드
     */
    List<Todo> findAllByUserAndDate(User user, LocalDate date);
}
