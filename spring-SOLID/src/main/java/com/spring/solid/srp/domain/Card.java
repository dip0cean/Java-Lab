package com.spring.solid.srp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

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
