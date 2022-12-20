package com.study.domain.entity;

import com.study.domain.embedded.AppleMusic;
import com.study.domain.embedded.Spotify;
import com.study.domain.embedded.YoutubeMusic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private long id;

    @Column(name = "email", columnDefinition = "varchar", updatable = false)
    private String email;

    @Column(name = "password", columnDefinition = "varchar")
    private String password;

    @Column(name = "nickname", columnDefinition = "varchar")
    private String nickname;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @Embedded
    private YoutubeMusic youtubeMusic;

    @Embedded
    private AppleMusic appleMusic;

    @Embedded
    private Spotify spotify;
}
