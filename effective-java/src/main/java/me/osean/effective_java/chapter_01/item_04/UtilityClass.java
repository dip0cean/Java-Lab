package me.osean.effective_java.chapter_01.item_04;

/**
 * 아이템 4. 인스턴스화를 막으려거든 private 생성자를 사용하라
 * 왜? > 정적 메소드만 담은 유틸리티성 클래스는 인스턴스로 만들어 쓰려고 설계한 클래스가 아니다. (가령 업무에서 사용하는 UniqueGenerator)
 * 추상 클래스로 만드는 것은 인스턴스화를 막을 수 없다. > 자식 클래스로 상속하여 구현하고, 자식 클래스를 통해 인스턴스화 가능하다.
 * 생성자에 주석으로 인스턴스화를 막으려는 이유를 기재하는 것이 좋다.
 * 상속을 방지할 때도 같은 방법을 사용할 수 있다. (class final)Ï
 */
// public abstract class UtilityClass {
public class UtilityClass {

    /**
     * 인스턴스를 생성하지 않도록 주석을 남기는 것이 좋다.
     * 해당 클래스는 인스턴스를 만들 수 없습니다.
     */
    private UtilityClass() {
        // Exception in thread "main" java.lang.AssertionError
        // 절대 생성자를 만나면 안되는 상황에서 아래의 Error 를 발생시켜 사용에 주의를 준다.
        throw new AssertionError();
    }

    public static String hello() {
        return "Hello!";
    }

    public static void main(String[] args) {
        String hello = UtilityClass.hello();
        // 정적 메소드만 존재하는 경우에 인스턴스화는 불필요한 코드, 메모리 낭비 등 문제를 야기한다.
        UtilityClass utilityClass = new UtilityClass();
        utilityClass.hello();
    }
}
