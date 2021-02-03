package com.ohzzi.todolist.service;

import com.ohzzi.todolist.controller.dto.TodoResponseDto;
import com.ohzzi.todolist.controller.dto.TodoSaveRequestDto;
import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long saveTodo(TodoSaveRequestDto dto) {
        return todoRepository.save(dto.ToEntity()).getId();
    }

    @Transactional
    public TodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 할일이 존재하지 않습니다."));
        return new TodoResponseDto(todo);
    }

    @Transactional
    public Long updateTodo(Long id, TodoUpdateRequestDto dto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 할일이 존재하지 않습니다."));
        todo.update(dto);
        return id;
    }

    @Transactional
    public Long deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 할일이 존재하지 않습니다."));
        todoRepository.delete(todo);
        return id;
    }

}
