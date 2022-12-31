package com.study.domain.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountType {

    MONEY("enumDiscountTypeMoneyDesc", "원 할인"), // 원화 할인
    PERCENT("enumDiscountTypePercentDesc", "% 할인"); //% 할인

    private final String desc;
    private final String ko;
}
