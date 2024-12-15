package com.cloudhub.barovillage.domain.user;

import com.cloudhub.barovillage.common.S3Service;
import com.cloudhub.barovillage.domain.user.model.request.PostUserLocationReq;
import com.cloudhub.barovillage.domain.user.model.response.GetCheckUserLocationAuthenticationRes;
import com.cloudhub.barovillage.domain.user.model.response.PostUserLocationRes;
import com.cloudhub.barovillage.domain.user.model.response.PostUserRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final S3Service s3Service;

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
        UserLocation lastUserLocation = userService.getLastUserLocation(loginUserId);

        Boolean result = lastUserLocation != null ? true : false;

        return ResponseEntity.ok(new GetCheckUserLocationAuthenticationRes(result));
    }

    /**
     * 위치 인증 등록
     * @param authorization
     * @param postUserLocationReq
     * @return
     */
    @PostMapping("/api/users/location")
    public ResponseEntity<PostUserLocationRes> postUserLocation (@RequestHeader("Authorization") String authorization,
                                                                 @RequestBody PostUserLocationReq postUserLocationReq) {
        Long loginUserId = Long.valueOf(authorization);
        Double latitude = postUserLocationReq.getLatitude();
        Double longitude = postUserLocationReq.getLongitude();

        PostUserLocationRes postUserLocationRes = userService.authUserLocation(loginUserId, latitude, longitude);

        return ResponseEntity.ok(postUserLocationRes);

    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = s3Service.uploadImage(file);
        return ResponseEntity.ok(fileUrl);
    }


}
