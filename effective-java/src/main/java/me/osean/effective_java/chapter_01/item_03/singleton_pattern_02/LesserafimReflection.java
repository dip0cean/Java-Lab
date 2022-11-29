package me.osean.effective_java.chapter_01.item_03.singleton_pattern_02;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Lesserafim;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LesserafimReflection {
    public static Lesserafim newInstance(boolean flag) {
        try {
            Constructor<Lesserafim> constructor = Lesserafim.class.getDeclaredConstructor();
            // Constructor 의 Accessible 값이 False 인 경우, 생성자가 private 으로 설정되기 때문에 새로운 인스턴스를 생성 할 수 없다.
            // cannot access a member of class with modifiers "private"
            constructor.setAccessible(flag);
            return constructor.newInstance();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
