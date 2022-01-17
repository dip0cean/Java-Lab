package com.spring.basic.chapter_13_Null_Safety;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    // 메소드에 해당 어노테이션을 적용하면 리턴값에 대한 Null-safety 를 보장한다.
    @NonNull
    // 파라미터에 해당 어노테이션을 적용하면 파라미터 인자로 받는 값에 대한 Null-Safety 를 보장한다.
    public String sayHello(@NonNull String sentence, String userName) {
        return userName + " : " + sentence;
    }
}
