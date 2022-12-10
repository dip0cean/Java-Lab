package com.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id", columnDefinition = "char")
    private String id;

    @Column(name = "name", columnDefinition = "varchar")
    private String name;

    @Column(name = "content", columnDefinition = "text")
    private String content;
}
