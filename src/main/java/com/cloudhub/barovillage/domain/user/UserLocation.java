package com.cloudhub.barovillage.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.cloudhub.barovillage.domain.user.UserLocation.UserLocationStatus.ACTIVE;

@Entity
@Table(name = "user_location_table")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_location_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = true)
    private Long kakaoRegionCode;

    @Column(nullable = true)
    private String dong;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserLocationStatus status;

    enum UserLocationStatus{
        ACTIVE, // 유효
        INACTIVE; // 만료
    }

    // 만료기간 자동 지정
    @PrePersist
    public void setExpiredAt() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(); // createdAt이 null이면 현재 시간으로 설정
        }
        this.expiredAt = createdAt.plusMonths(1); // 한 달 뒤
        this.status = ACTIVE;
    }

    @Builder
    public UserLocation(Long id, User user, Double latitude, Double longitude, Long kakaoRegionCode, String dong, LocalDateTime expiredAt, LocalDateTime createdAt, UserLocationStatus status) {
        this.id = id;
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kakaoRegionCode = kakaoRegionCode;
        this.dong = dong;
        this.expiredAt = expiredAt;
        this.createdAt = createdAt;
        this.status = status;
    }

    public void setStatus(UserLocationStatus status) {
        this.status = status;
    }
}
