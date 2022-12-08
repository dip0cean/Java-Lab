package com.study.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    private String id;
    @Column(updatable = false)
    private String num; // 시퀀스 번호
    private String uid; // UID
    private LocalDate birth; // 생년월일
    private String loginId; // 로그인 ID
    private String phoneNumber; // 유저 연락처
    private String email; // 유저 이메일
    private String password; // 비밀번호
    private String fullName; // 전체 이름
    private String promotionCode; // 프로모션 코드
    private String profileImageUrl; // 유저 프로필 이미지
    private String firebaseToken; // 파이어베이스 토큰
    @Enumerated(EnumType.STRING)
    private SignupType signupType; // 가입 유형
    @Enumerated(EnumType.STRING)
    private OsPlatform signupOsPlatform; // 가입 플랫폼
    private String signupOsPlatformVersion; // 가입 당시 플랫폼 버전
    private String signupAppVersion; // 가입 당시 앱 버전
    @Enumerated(EnumType.STRING)
    private Role role; // 등급 (역할)
    @Enumerated(EnumType.STRING)
    private Status status = Status.USED; // 현재 계정 상태
    private String userMensesContentsId;
    private String userMessageId;
    private Boolean blacklisted = false;
    private String whyBlacklisted;
    private Boolean freePassFlag = false;

    @Getter
    @AllArgsConstructor
    public enum SignupType {
        ADMIN("ADMIN"),
        EMAIL("EMAIL"),
        FACEBOOK("FACEBOOK"),
        APPLE("APPLE"),
        GOOGLE("GOOGLE");

        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum OsPlatform {
        WEB("WEB"),
        IOS("IOS"),
        ANDROID("AOS");

        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum Role {
        GENERAL("MEMBER"),
        ADMIN("MANAGER");

        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        UNUSED("UNUSED"),
        USED("USED");

        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum Gender {
        FEMALE("Female", "여"),
        MALE("Male", "남");

        private final String desc;
        private final String ko;
    }
}
