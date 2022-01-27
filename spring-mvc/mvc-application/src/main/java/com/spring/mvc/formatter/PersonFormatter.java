package com.spring.mvc.formatter;

import com.spring.mvc.model.Person;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component // Spring Boot 를 사용하는 경우, Formatter 를 Bean 으로 등록해주면 알아서 Formatter 설정을 수행한다.
public class PersonFormatter implements Formatter<Person> {
    @Override
    public Person parse(String text, Locale locale) throws ParseException {
        return new Person(text);
    }

    @Override
    public String print(Person object, Locale locale) {
        return object.toString();
    }
}
