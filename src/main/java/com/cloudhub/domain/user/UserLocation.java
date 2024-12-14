package com.cloudhub.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_location_table")
@Getter
public class UserLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_location_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = true)
    private String si;

    @Column(nullable = true)
    private String gu;

    @Column(nullable = true)
    private String dong;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private UserLocationStatus status;

    enum UserLocationStatus{
        ACTIVE, // 유효
        INACTIVE; // 만료
    }

    @Builder
    public UserLocation(Long id, Double latitude, Double longitude, String si, String gu, String dong, LocalDateTime expiredAt, LocalDateTime createdAt, UserLocationStatus status) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.si = si;
        this.gu = gu;
        this.dong = dong;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
        this.status = status;
    }
}
