package me.osean.effective_java.chapter_01.item_02.illegal_argument_exception;

import java.time.LocalDate;

public class Order {

    public void updateDeliveryDate(LocalDate deliveryDate)
        // 메소드 선언부에서 Runtime Exception 을 선언하지 않아도 되지만
        // 이를 선언해주는 경우, 클라이언트 입장에서 해당 메소드가 어떤 경우에 예외를 발생시키는지 쉽게 파악 할 수 있다.
            throws IllegalArgumentException, NullPointerException {
        if (deliveryDate == null) {
            throw new NullPointerException("deliveryDate can't be null");
        }
        if (deliveryDate.isBefore(LocalDate.now())) {
            // 현재보다 과거인 값을 인자로 받을 때 예외처리
            // > 예외를 던질 때는 어떤 인자가 잘못되었는가, 어떤 근거를 가지는가를 함께 던지는 것이 클라이언트가 예외를 파악하는데 수월하다.
            throw new IllegalArgumentException("deliveryDate can't be earlier than " + LocalDate.now());
        }
    }
}
