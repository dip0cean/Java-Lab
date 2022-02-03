package com.spring.mvc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement // Setter 메소드가 존재하지 않으면 XML 데이터로 응답할 때 내부 필드값을 함께 넘길 수 없다.
@Entity
public class Cat {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
