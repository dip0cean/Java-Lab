package me.osean.effective_java.chapter_01.item_05.spring_ioc.component_scan;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Concert {

    private List<Singer> singers;

    private Concert(List<Singer> singers) {
        this.singers = singers;
    }

    public void showtime() {
        this.singers.forEach(Singer::sing);
    }
}
