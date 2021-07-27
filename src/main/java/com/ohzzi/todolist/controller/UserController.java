package com.ohzzi.todolist.controller;

import com.ohzzi.todolist.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final HttpSession httpSession;

    /**
     * 세션에 저장된 유저 정보를 가져오는 메소드
     */
    @GetMapping("/api/user")
    public SessionUser getUser() {
        return (SessionUser) httpSession.getAttribute("user");
    }
}
