package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    private String id;

    private String name;

    private Integer age;

    private SEX sex;

    @Getter
    public enum SEX {
        MALE, FEMALE
    }
}
