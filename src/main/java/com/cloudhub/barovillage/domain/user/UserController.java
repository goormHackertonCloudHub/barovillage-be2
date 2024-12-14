package com.cloudhub.barovillage.domain.user;

import com.cloudhub.barovillage.domain.user.model.response.PostUserRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<PostUserRes> postUser() {
        User signedUpUser = userService.signUp();
        PostUserRes postUserRes = new PostUserRes(signedUpUser.getId(), signedUpUser.getNickname());
        return ResponseEntity.ok(postUserRes);
    }


}
