package me.osean.effective_java.chapter_06.item_34;

/**
 * 과거의 정수 열거 상수 패턴
 * - typesafe 를 보장할 수 없다.
 * - 표현력이 좋지 않다.
 * - 정수 열거 상수 패턴은 별도의 NameSpace 를 제공하지 않기 때문에 접두어를 사용해 이름 충돌을 방지한다.
 * - 비교 동등 연산자를 이용해 비교하더라도 경고 메세지를 출력하지 않는다.
 * <p>
 * 이러한 문제 때문에 정수 열거 패턴을 사용한 프로그램은 깨지기가 쉽다.
 * 상수값이 바뀌면 클라이언트가 그에 맞춰서 새롭게 컴파일 해야 하는 과정이 필요하고,
 * 디버깅하여 데이터를 확인하더라도 숫자로 이루어져 있어 그 의미를 단번에 파악하기가 힘들다.
 * <p>
 * 문자열 열거 패턴을 사용하여 정수 열거 패턴의 문제인 의미를 파악하기 힘든 문제를 해결할 수는 있으나
 * 컴파일 과정에서 오타를 발견할 수 없으니 런타임 에러가 발생한다.
 */
public class Fruit {

    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;
}
