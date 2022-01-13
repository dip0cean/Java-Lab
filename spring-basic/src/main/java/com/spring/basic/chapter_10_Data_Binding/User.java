package com.spring.basic.chapter_10_Data_Binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

    public User(Integer id) {
        this.id = id;
    }
}
