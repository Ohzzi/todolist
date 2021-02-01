package com.ohzzi.todolist.domain.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BooleanToYnConverterTest {

    @Autowired
    BooleanToYnConverter booleanToYnConverter;

    @Test
    void Boolean을_YN으로_바꾼다() {
        // given
        Boolean T = true;
        Boolean F = false;

        // when
        assertThat(booleanToYnConverter.convertToDatabaseColumn(T)).isEqualTo("Y");
        assertThat(booleanToYnConverter.convertToDatabaseColumn(F)).isEqualTo("N");
        assertThat(booleanToYnConverter.convertToDatabaseColumn(null)).isEqualTo("N");
    }

    @Test
    void YN을_Boolean으로_바꾼다() {
        // given
        String Y = "Y";
        String N = "N";

        // when
        assertThat(booleanToYnConverter.convertToEntityAttribute(Y)).isEqualTo(true);
        assertThat(booleanToYnConverter.convertToEntityAttribute(N)).isEqualTo(false);
    }
}