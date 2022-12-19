package com.study.util.converter;

import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EnumListConverter<E extends Enum<E>> implements AttributeConverter<EnumSet<E>, String> {

    private final Class<E> enumClass;

    @Override
    public String convertToDatabaseColumn(EnumSet<E> enums) {
        if (enums == null || enums.size() < 1) {
            return null;
        }

        return enums.stream().map(Enum::name).collect(Collectors.joining("|"));
    }

    @Override
    public EnumSet<E> convertToEntityAttribute(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        String[] names = StringUtils.split(name, "|");
        Map<String, E> nameMap = EnumSet.allOf(enumClass).stream().collect(Collectors.toMap(Enum::name, Function.identity()));
        List<E> enums = Arrays.stream(names).map(nameMap::get).collect(Collectors.toList());
        return EnumSet.copyOf(enums);
    }
}
