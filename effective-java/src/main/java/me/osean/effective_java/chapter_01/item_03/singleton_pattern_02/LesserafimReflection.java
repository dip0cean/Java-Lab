package me.osean.effective_java.chapter_01.item_03.singleton_pattern_02;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Lesserafim;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LesserafimReflection {

    public static void main(String[] args) {
        //단점 2. 리플렉션으로 private 생성자를 호출할 수 있다.
        Lesserafim lesserafim_1 = LesserafimReflection.newInstance(true);
        Lesserafim lesserafim_2 = Lesserafim.INSTANCE;
        System.out.println(lesserafim_1 == lesserafim_2);
        // Constructor 의 Accessible 필드가 False 인 경우, 생성자가 private 으로 설정되어 새로운 인스턴스를 생성하지 못하고 예외 처리한다.
        Lesserafim lesserafim_3 = LesserafimReflection.newInstance(false);
        System.out.println(lesserafim_3);
    }

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
