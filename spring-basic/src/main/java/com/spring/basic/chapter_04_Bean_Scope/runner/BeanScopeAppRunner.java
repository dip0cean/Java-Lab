package com.spring.basic.chapter_04_Bean_Scope.runner;

import com.spring.basic.chapter_04_Bean_Scope.component.Proto;
import com.spring.basic.chapter_04_Bean_Scope.component.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeanScopeAppRunner implements ApplicationRunner {

    private final ApplicationContext ctx;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
        log.info("Single :: {}", ctx.getBean(Single.class));
        log.info("Single :: {}", ctx.getBean(Single.class));
        log.info("Single :: {}", ctx.getBean(Single.class));
        System.out.println();
        log.info("Single By Proto :: {}", ctx.getBean(Single.class).getProto());
        log.info("Single By Proto :: {}", ctx.getBean(Single.class).getProto());
        log.info("Single By Proto :: {}", ctx.getBean(Single.class).getProto());
        System.out.println();
        log.info("Proto :: {}", ctx.getBean(Proto.class));
        log.info("Proto :: {}", ctx.getBean(Proto.class));
        log.info("Proto :: {}", ctx.getBean(Proto.class));
        System.out.println();
//        log.info("Proto By Single :: {}", ctx.getBean(Proto.class).getSingle());
//        log.info("Proto By Single :: {}", ctx.getBean(Proto.class).getSingle());
//        log.info("Proto By Single :: {}", ctx.getBean(Proto.class).getSingle());
    }
}
