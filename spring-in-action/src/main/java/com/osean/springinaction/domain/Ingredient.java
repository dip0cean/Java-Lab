package com.osean.springinaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Ingredient {

    private final String id; // 재료 ID
    private final String name; // 재료명
    private final Type type; // 재료 타입

    // 재료 타입 Enum
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
