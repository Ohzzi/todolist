package com.ohzzi.todolist.service;

import com.ohzzi.todolist.controller.dto.TodoResponseDto;
import com.ohzzi.todolist.controller.dto.TodoSaveRequestDto;
import com.ohzzi.todolist.controller.dto.TodoUpdateRequestDto;
import com.ohzzi.todolist.domain.todo.Todo;
import com.ohzzi.todolist.domain.todo.TodoRepository;
import com.ohzzi.todolist.domain.user.User;
import com.ohzzi.todolist.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<TodoResponseDto> getTodoByUser(String userEmail, String dateString) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        List<Todo> todoList = todoRepository.findAllByUser(user);
        List<TodoResponseDto> result = new ArrayList<>();
        for(Todo todo:todoList) {
            if (todo.getDate().isEqual(date)) {
                result.add(new TodoResponseDto(todo));
            }
        }
        return result;
    }

    @Transactional
    public Long saveTodo(TodoSaveRequestDto dto) {
        User user = userRepository.findByEmail(dto.getUser().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));
        dto.setUser(user);
        Todo todo = dto.ToEntity();
        todo.addTodoToUser(todo.getUser());
        return todoRepository.save(todo).getId();
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
