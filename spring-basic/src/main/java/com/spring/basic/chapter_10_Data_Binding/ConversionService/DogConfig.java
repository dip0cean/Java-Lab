package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DogConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        // Converter
        registry.addConverter(new DogConverter.StringToDtoConverter());

        /*
        아래의 Converter 들은 Dto <-> Entity 간 변환을 도와주는 객체들이다.
        @Component 어노테이션을 통해 Bean 객체로 등록되어 Spring 이 알아서 컨버팅 작업을 수행한다.

        > 주저리
            WebMVC 에서는 Request 받은 JSON 형 데이터를 자동으로 클래스에 맞게 변환해주기도 하고
            Converter 들을 Bean 으로도 등록할 수 있기 때문에 Join 이 걸려있는 데이터를 변환해주기 위해
            서버 자체적으로 사용하는 건 어떨까? 라는 생각이 든다.

        - registry.addConverter(new DogConverter.DtoToEntityConverter());
        - registry.addConverter(new DogConverter.EntityToDtoConverter());
         */

        // Formatter
        registry.addFormatter(new DogFormatter());
    }
}
