package com.spring.solid.srp.domain;

import lombok.*;

import java.time.LocalDateTime;

public class CardDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class Request {
        // 카드 번호
        private String cardNum;
        // CVC
        private String cvc;
        // 유효기간
        private LocalDateTime expirationDate;
        // 결제 한도
        private Integer limitAmount;
        // 카드 타입
        private CardType cardType;
    }
}
