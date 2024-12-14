package com.cloudhub.barovillage.domain.user;

import com.cloudhub.barovillage.domain.user.UserLocation.UserLocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    Optional<UserLocation> findByUserAndStatus(User user, UserLocationStatus status);
}
