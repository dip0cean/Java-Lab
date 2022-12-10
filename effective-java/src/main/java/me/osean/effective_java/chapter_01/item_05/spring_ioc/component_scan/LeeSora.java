package me.osean.effective_java.chapter_01.item_05.spring_ioc.component_scan;

import org.springframework.stereotype.Component;

@Component
public class LeeSora implements Singer {
    @Override
    public void sing() {
        System.out.println("이제 그만~ 돌아오라고 안해요~");
    }
}
