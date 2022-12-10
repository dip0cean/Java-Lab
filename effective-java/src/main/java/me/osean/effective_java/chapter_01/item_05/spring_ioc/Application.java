package me.osean.effective_java.chapter_01.item_05.spring_ioc;

import me.osean.effective_java.chapter_01.item_05.spring_ioc.bean.BeanConfig;
import me.osean.effective_java.chapter_01.item_05.spring_ioc.bean.SpellChecker;
import me.osean.effective_java.chapter_01.item_05.spring_ioc.component_scan.ComponentScanConfig;
import me.osean.effective_java.chapter_01.item_05.spring_ioc.component_scan.Concert;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class, ComponentScanConfig.class);
        SpellChecker spellChecker = applicationContext.getBean(SpellChecker.class);
        spellChecker.isValid("안녕하세요");

        Concert concert = applicationContext.getBean(Concert.class);
        concert.showtime();
    }
}
