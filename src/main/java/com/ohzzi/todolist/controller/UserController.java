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

    @GetMapping("/api/user")
    public SessionUser getUser() {
        return (SessionUser) httpSession.getAttribute("user");
    }
}
