package com.ohzzi.todolist.controller;

import com.ohzzi.todolist.controller.dto.TodoResponseDto;
import com.ohzzi.todolist.controller.dto.TodoSaveRequestDto;
import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/v1/todo")
    public Long saveTodo(@RequestBody TodoSaveRequestDto dto) {
        return todoService.saveTodo(dto);
    }

    @GetMapping("/api/v1/todo/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    @PutMapping("/api/v1/todo/{id}")
    public Long UpdateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto dto) {
        return todoService.updateTodo(id, dto);
    }

}
