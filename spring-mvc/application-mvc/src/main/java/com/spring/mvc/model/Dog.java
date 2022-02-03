package com.spring.mvc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
