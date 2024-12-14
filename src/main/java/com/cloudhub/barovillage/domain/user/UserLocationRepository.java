package com.cloudhub.barovillage.domain.user;

import com.cloudhub.barovillage.domain.user.UserLocation.UserLocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    List<UserLocation> findAllByUserAndStatus(User user, UserLocationStatus status);
}
