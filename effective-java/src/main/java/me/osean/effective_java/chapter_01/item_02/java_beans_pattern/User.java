package me.osean.effective_java.chapter_01.item_02.java_beans_pattern;

import java.time.LocalDate;

public class User {
    public enum Sex {MALE, FEMALE}

    private String id; // (필수) 로그인 ID
    private String password; // (필수) 로그인 패스워드
    private String nickname; // (필수) 닉네임
    private Sex sex;
    private LocalDate birth;

    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
