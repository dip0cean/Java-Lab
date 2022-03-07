package com.spring.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class Study {

    private String  name;
    private Status  status = Status.DRAFT;
    private Integer limit;

    public Study(Integer limit, String name) {
        this.limit = limit;
        this.name = name;
        if (!StringUtils.hasText(name)) {
            throw new NullPointerException("스터디명을 입력해주세요.");
        }
    }

    public enum Status {
        DRAFT, STARTED, ENDED
    }
}
