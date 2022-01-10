package com.spring.basic.chapter_04_Bean_Scope.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Single {

//    private final Proto proto;

//    public Proto getProto() {
//        return proto;
//    }

    private final ObjectProvider<Proto> proto;

    public Proto getProto() {
        return proto.getIfAvailable();
    }
}
