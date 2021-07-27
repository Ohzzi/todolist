package com.ohzzi.todolist.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * User 의 attribute 중 email 을 가지고 일치하는 User 를 찾아서 반환하는 메소드
     */
    Optional<User> findByEmail(String email);
}
