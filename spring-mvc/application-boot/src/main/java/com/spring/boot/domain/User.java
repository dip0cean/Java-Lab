package com.spring.boot.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class User {

    // @Validate 에서 해당 인터페이스를 value 로 지정하면 해당 인터페이스만 Valid 작업을 수행한다.
    public interface JobCheck {
    }

    public interface AgeCheck {
    }

    private String   id;
    private String   name;
    @NotNull(groups = JobCheck.class)
    private String[] jobs;
    @Min(value = 0, groups = AgeCheck.class)
    private Integer  age;
}
