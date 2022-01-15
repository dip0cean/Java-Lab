package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class DogConverter {

    /**
     * (SpringBoot) Converter 를 구현한 객체들은 모두 Thread-Safe 하기 때문에 Component 로 등록할 수 있으며,
     * Spring Bean 객체를 주입 받을 수 있다.
     * -> @PathVariable 인자로 받는 ID 와 Service / Repository 를 이용해서 알아서 Dto 혹은 Entity 로 변환해주도록 설계한다면 어떨까?
     */
    public static class StringToDtoConverter implements Converter<String, DogDto> {

        @Override
        public DogDto convert(String id) {
            // Repository 대체
            String name = "꼬망";
            if (id.equals("1")) name = "또익";

            return DogDto
                    .builder()
                    .id(id)
                    .name(name)
                    .build();
        }
    }

    /**
     * Dog -> DogDto 로 컨버팅
     */
    @Component
    public static class EntityToDtoConverter implements Converter<DogDto, Dog> {

        @Override
        public Dog convert(DogDto dogDto) {
            return Dog
                    .builder()
                    .id(dogDto.getId())
                    .name(dogDto.getName())
                    .age(dogDto.getAge())
                    .sex(dogDto.getSex())
                    .build();
        }
    }

    /**
     * DogDto -> Dog 로 컨버팅
     */
    @Component
    public static class DtoToEntityConverter implements Converter<Dog, DogDto> {

        @Override
        public DogDto convert(Dog dog) {
            return DogDto
                    .builder()
                    .id(dog.getId())
                    .name(dog.getName())
                    .age(dog.getAge())
                    .sex(dog.getSex())
                    .build();
        }
    }
}
