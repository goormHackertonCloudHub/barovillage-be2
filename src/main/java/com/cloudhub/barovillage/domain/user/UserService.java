package com.cloudhub.barovillage.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;

    // 회원 등록
    @Transactional
    public User signUp() {
        User savedUser = userRepository.save(new User());

        return savedUser.setNickname("유저" + savedUser.getId());

    }
}
