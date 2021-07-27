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

    /**
     * 전달받은 user 정보를 가지고 해당 user가 작성한 모든 할 일들을 가져오는 메소드
     */
    @Transactional
    public List<TodoResponseDto> getTodoByUser(String userEmail, String dateString) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        List<Todo> todoList = todoRepository.findAllByUserAndDate(user, date);
        List<TodoResponseDto> result = new ArrayList<>();
        for(Todo todo:todoList) {
            result.add(new TodoResponseDto(todo));
        }
        return result;
    }

    /**
     * 웹 페이지에서 작성한 할 일 정보를 받아서 레포지토리에 저장하는 메소드
     */
    @Transactional
    public Long saveTodo(TodoSaveRequestDto dto) {
        User user = userRepository.findByEmail(dto.getUser().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));
        dto.setUser(user);
        Todo todo = dto.toEntity();
        todo.addTodoToUser(todo.getUser());
        return todoRepository.save(todo).getId();
    }

    /**
     * 할 일의 번호를 가지고 정보를 가져오는 메소드. getTodoByUser 와는 다르게 user 정보가 아니라
     * DB에 저장할 때 자동으로 부여되는 고유 id를 가지고 조회한다.
     */
    @Transactional
    public TodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 할일이 존재하지 않습니다."));
        return new TodoResponseDto(todo);
    }

    /**
     * 웹 페이지에서 할 일의 내용이 수정되었을 경우 데이터베이스에 update 쿼리를 날리기 위한 메소드
     */
    @Transactional
    public Long updateTodo(Long id, TodoUpdateRequestDto dto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 할일이 존재하지 않습니다."));
        todo.update(dto);
        return id;
    }

    /**
     * 웹 페이지에서 할 일 삭제 요청이 들어왔을 경우 데이터베이스에 delete 쿼리를 날리기 위한 메소드
     */
    @Transactional
    public Long deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 할일이 존재하지 않습니다."));
        todoRepository.delete(todo);
        return id;
    }

}
