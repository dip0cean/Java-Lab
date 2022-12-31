package com.study.domain.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformType {

    ALL("enumProductSalesplatformAllDesc", "웹/앱 모두 노출"),
    WEB("enumProductSalesplatformWebDesc", "웹에서만 노출"),
    APP("enumProductSalesplatformAppDesc", "앱에서만 노출");

    private final String desc;
    private final String ko;

    public boolean isWeb() {
        return this == WEB || this == ALL;
    }

    public boolean isApp() {
        return this == APP || this == ALL;
    }
}
