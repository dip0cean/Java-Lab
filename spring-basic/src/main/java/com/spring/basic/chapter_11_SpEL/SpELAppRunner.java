package com.spring.basic.chapter_11_SpEL;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:/app.yml")
public class SpELAppRunner implements ApplicationRunner {

    @Value("#{1 + 1}")
    int value;

    @Value("#{'hello' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    @Value("#{${value} eq 1000}")
    boolean eqValue;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("value? :: {}", value);
        log.info("greeting :: {}", greeting);
        log.info("trueOrFalse :: {}", trueOrFalse);
        log.info("eqValue :: {}", eqValue);

        // Cat Entity 의 인스턴스 생성
        Cat cat = new Cat("1", "나비");

        // ConversionService 를 이용해 SpEl 표현식을 변환하는 작업을 수행한다.
        ExpressionParser parser = new SpelExpressionParser();
        // Spring Bean 객체가 가지고 있는 객체 정보를 구하는 역할을 한다.
        StandardEvaluationContext context = new StandardEvaluationContext(cat);
        // 인자로 받은 SpEL 표현식을 해석한다.
        Expression expression = parser.parseExpression("name eq '나비'");
        // 해석된 표현식을 이용해 Context 로 등록된 객체에 대한 값을 추출한다.
        Boolean value = expression.getValue(context, Boolean.class);

        log.info("cat.name.equals(\"나비\") :: {}", value);
    }
}
