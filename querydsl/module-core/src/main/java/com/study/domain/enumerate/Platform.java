package com.study.domain.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@Getter
@AllArgsConstructor
public enum Platform {
    ALL("모든 플랫폼", "웹/앱 모두 노출"),
    WEB("웹 플랫폼", "웹에서만 노출"),
    APP("앱 플랫폼", "앱에서만 노출");

    private final String desc;
    private final String ko;

    public static EnumSet<Platform> getSetFromPlatform(boolean isApp) {
        return EnumSet.of(Platform.ALL, isApp ? Platform.APP : Platform.WEB);
    }
}
