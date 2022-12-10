package me.osean.effective_java.chapter_01.item_05.spring_ioc.component_scan;

import org.springframework.stereotype.Component;

@Component
public class Kirinji implements Singer {
    @Override
    public void sing() {
        System.out.println("遥か空に旅客機 音もなく~ 公団の屋根の上 どこへ行く~");
    }
}
