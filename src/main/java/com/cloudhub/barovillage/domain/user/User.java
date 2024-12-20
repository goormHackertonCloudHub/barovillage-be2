package com.cloudhub.barovillage.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user_table")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = true)
    private String nickname;

    @Column(columnDefinition = "VARCHAR(5000)", nullable = true)
    private String profileImageUrl;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public User(Long id, String nickname, String profileImageUrl, LocalDateTime createdAt, UserLocation userLocation) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
