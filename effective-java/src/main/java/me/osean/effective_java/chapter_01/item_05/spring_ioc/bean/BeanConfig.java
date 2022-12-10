package me.osean.effective_java.chapter_01.item_05.spring_ioc.bean;

import me.osean.effective_java.chapter_01.item_05.dependency_injection.Dictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SpringDictionary dictionary() {
        return new SpringDictionary();
    }

    @Bean
    SpellChecker spellChecker(Dictionary dictionary) {
        return new SpellChecker(dictionary);
    }
}
