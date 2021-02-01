package com.ohzzi.todolist;

import com.ohzzi.todolist.domain.todo.BooleanToYnConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public BooleanToYnConverter booleanToYnConverter() {
        return new BooleanToYnConverter();
    }
}
