package com.ohzzi.todolist.domain.todo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Bool 논리 값을 "Y"와 "N"값으로 데이터베이스에 저장할 수 있도록 변환해 주는 클래스
 */
@Converter(autoApply = true)
public class BooleanToYnConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute != null && attribute) {
            return "Y";
        } else {
            return "N";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
