package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DogController {

    // DogConverter.EntityToDtoConverter 객체는 @Component 이기 때문에 Bean 으로 등록된 객체들은 모두 주입 받을 수 있다.
    private final DogConverter.EntityToDtoConverter entityToDtoConverter;

    /**
     * DogConverter.StringToDtoConverter 클래스를 이용해
     * URL 인자로 받은 dogId 를 DogDto 로 컨버팅해준다.
     *
     * @param dogDto DogDto (-> String dogId)
     * @return String
     */
    @GetMapping("/dogDto/{dogId}")
    public String getDogDto(@PathVariable("dogId") DogDto dogDto) {
        log.info("DogDto :: {}", dogDto);

        // Dog Entity
        Dog dog = entityToDtoConverter.convert(dogDto);
        log.info("Dog :: {}", dog);

        return dogDto.getName();
    }

    @GetMapping("/dog/{dogId}")
    public String getDog(@PathVariable("dogId") Dog dog) {
        log.info("Dog :: {}", dog);

        return dog.getName();
    }
}