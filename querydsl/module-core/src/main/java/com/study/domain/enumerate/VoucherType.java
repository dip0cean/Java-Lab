package com.study.domain.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoucherType {
    NORMAL("일반 제품"), //일반제품
    SANITARY_PAD("생리 용품"), //생리용품
    NAPPY("기저귀 제품"); //기저귀제품

    private final String desc;
}
