package com.cloudhub.barovillage.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(columnDefinition = "VARCHAR(5000)", nullable = true)
    private String profileImageUrl;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_location_id")
    private UserLocation userLocation;

    @Builder
    public User(Long id, String nickname, String profileImageUrl, LocalDateTime createdAt, UserLocation userLocation) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.userLocation = userLocation;
    }
}
