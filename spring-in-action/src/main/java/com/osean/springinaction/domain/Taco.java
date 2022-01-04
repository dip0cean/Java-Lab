package com.osean.springinaction.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Taco {

    private String name; // Taco 인스턴스의 이름
    private List<String> ingredients; // Taco 인스턴스의 재료들
}
