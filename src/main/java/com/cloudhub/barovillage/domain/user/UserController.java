package com.cloudhub.barovillage.domain.user;

import com.cloudhub.barovillage.domain.user.model.request.PostUserLocationReq;
import com.cloudhub.barovillage.domain.user.model.response.GetCheckUserLocationAuthenticationRes;
import com.cloudhub.barovillage.domain.user.model.response.PostUserLocationRes;
import com.cloudhub.barovillage.domain.user.model.response.PostUserRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 유저 등록
     * @return
     */
    @PostMapping("/api/users")
    public ResponseEntity<PostUserRes> postUser() {
        User signedUpUser = userService.signUp();
        PostUserRes postUserRes = new PostUserRes(signedUpUser.getId(), signedUpUser.getNickname());
        return ResponseEntity.ok(postUserRes);
    }

    /**
     * 유저의 위치 인증 여부 확인
     * @param authorization
     * @return
     */
    @GetMapping("/api/users/location")
    public ResponseEntity<GetCheckUserLocationAuthenticationRes> checkUserLocationAuthentication(
            @RequestHeader("Authorization") String authorization) {
        Long loginUserId = Long.valueOf(authorization);
        Boolean result = userService.checkUserLocationStatus(loginUserId);

        return ResponseEntity.ok(new GetCheckUserLocationAuthenticationRes(result));
    }

//    @PostMapping("/api/users/location")
//    public ResponseEntity<PostUserLocationRes> postUserLocation (@RequestHeader("Authorization") String authorization,
//                                                                 @RequestBody PostUserLocationReq postUserLocationReq) {
//        Long loginUserId = Long.valueOf(authorization);
//        Double latitude = postUserLocationReq.getLatitude();
//        Double longitude = postUserLocationReq.getLongitude();
//
//        userService.authUserLocation(loginUserId, latitude, longitude);
//
//        return null;
//
//    }


}
