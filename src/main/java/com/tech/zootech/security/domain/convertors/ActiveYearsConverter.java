package com.tech.zootech.security.domain.convertors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Converter(autoApply = true)
public class ActiveYearsConverter implements AttributeConverter<List<Integer>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        if (isNull(attribute) || attribute.isEmpty()) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            attribute.forEach(it -> sb.append(it).append(","));
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        if (isNull(dbData) || dbData.isBlank()) {
            return Collections.emptyList();
        } else {
            return Arrays.stream(dbData.split(DELIMITER))
                    .map(Integer::valueOf)
                    .toList();
        }
    }
}
