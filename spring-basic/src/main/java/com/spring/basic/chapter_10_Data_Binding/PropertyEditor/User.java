package com.spring.basic.chapter_10_Data_Binding.PropertyEditor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Integer id;

    private String name;

    @Builder
    public User(Integer id) {
        this.id = id;
    }
}
