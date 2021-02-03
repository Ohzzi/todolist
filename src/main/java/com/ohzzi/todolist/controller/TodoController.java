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

    @PostMapping("/api/todo")
    public Long saveTodo(@RequestBody TodoSaveRequestDto dto) {
        return todoService.saveTodo(dto);
    }

    @GetMapping("/api/todo/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    @PutMapping("/api/todo/{id}")
    public Long updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto dto) {
        return todoService.updateTodo(id, dto);
    }

    @DeleteMapping("/api/todo/{id}")
    public Long deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

}
