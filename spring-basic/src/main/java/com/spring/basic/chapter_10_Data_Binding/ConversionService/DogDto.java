package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogDto {

    private String id;

    private String name;

    private Integer age;

    private Dog.SEX sex;
}
