package com.cloudhub.barovillage.domain.user;

import com.cloudhub.barovillage.common.dto.KakaoAddressResponse;
import com.cloudhub.barovillage.common.dto.KakaoAddressResponse.Document;
import com.cloudhub.barovillage.domain.user.model.dto.UserLocationDto;
import com.cloudhub.barovillage.domain.user.model.response.PostUserLocationRes;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cloudhub.barovillage.domain.user.UserLocation.UserLocationStatus.ACTIVE;
import static com.cloudhub.barovillage.domain.user.UserLocation.UserLocationStatus.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String KAKAO_API_KEY = "a8d8900f9fde559786a378c9c60a2171";

    // 회원 등록
    @Transactional
    public User signUp() {
        User savedUser = userRepository.save(new User());

        return savedUser.setNickname("유저" + savedUser.getId());
    }

    // 유저 위치인증 여부 확인
    @Transactional
    public UserLocation getLastUserLocation(Long loginUserId) {
        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        // 유저 위치인증 상태 업데이트 및 확인
        Optional<UserLocation> userLocationOptional = userLocationRepository.findByUserAndStatus(user, ACTIVE);
        if (userLocationOptional.isEmpty()) { // 없다면
            return null;
        } else { // 있다면
            UserLocation userLocation = updateUserLocationStatus(userLocationOptional.get()); // 업데이트 해보고
            if (userLocation.getStatus() == ACTIVE) // 그래도 ACTIVE이면
                return userLocation;
            else // INACTIVE가 되었다면
                return null;
        }

    }

    // 유저 위치인증 상태 업데이트
    private UserLocation updateUserLocationStatus(UserLocation userLocation) {
        if (userLocation.getStatus() == ACTIVE) { // ACTIVE 이면
            if (LocalDateTime.now().isAfter(userLocation.getExpiredAt())) // 검사
                userLocation.setStatus(INACTIVE);
        }
        return userLocation;
    }

    // 유저 위치인증
    @Transactional
    public PostUserLocationRes authUserLocation(Long loginUserId, Double latitude, Double longitude) {
        User user = userRepository.findById(loginUserId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        UserLocation lastUserLocation = getLastUserLocation(user.getId());
        if (lastUserLocation != null) // 유효한 위치 인증이 존재한다면
            lastUserLocation.setStatus(INACTIVE); // 무효화하고 진행

        UserLocationDto userLocationDto = requestAddressByCoordinate(latitude, longitude);

        if(userLocationDto == null)
            throw new IllegalArgumentException("행정동 조회에 실패했습니다.");

        Long regionCode = Long.valueOf(userLocationDto.getCode());
        String lastAddressName = userLocationDto.getLastAddressName();

        UserLocation userLocation = UserLocation.builder()
                .user(user)
                .latitude(latitude)
                .longitude(longitude)
                .kakaoRegionCode(regionCode)
                .status(ACTIVE)
                .dong(lastAddressName)
                .build();
        userLocationRepository.save(userLocation);

        return PostUserLocationRes.builder()
                .regionCode(regionCode)
                .regionName(lastAddressName)
                .userId(user.getId())
                .build();


    }

    // 카카오 로컬 API 요청 전송
    private UserLocationDto requestAddressByCoordinate(Double latitude, Double longitude) {
        String apiKey = "KakaoAK " + KAKAO_API_KEY; // API 키
        String baseUrl = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";

        // URL에 쿼리스트링 추가
        String url = baseUrl + "?x=" + longitude + "&y=" + latitude;

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);

        // 요청 전송
        ResponseEntity<KakaoAddressResponse> response = sendGetRequest(url, headers);

        // 특정 필드 추출
        Document document;
        List<Document> HtypeRegionDocumentList;
        HtypeRegionDocumentList = response.getBody().getDocuments().stream().filter(d -> d.getRegion_type().equals("H")).toList();
        if(HtypeRegionDocumentList.isEmpty() || HtypeRegionDocumentList.size() < 1) // 행정동이 없다면
            HtypeRegionDocumentList = response.getBody().getDocuments().stream().filter(d -> d.getRegion_type().equals("H")).toList();
        document = HtypeRegionDocumentList.get(0);
        String regionCode = document.getCode();
        String lastAddressName = getLastAddressNameFromKakaoDocument(document);

        return new UserLocationDto(regionCode, lastAddressName);
    }

    private String getLastAddressNameFromKakaoDocument(Document document) {
        // 마지막 객체의 region_* 필드를 조합
        List<String> addressList = new ArrayList<>();
        addressList.add(document.getRegion_1depth_name());
        addressList.add(document.getRegion_2depth_name());
        addressList.add(document.getRegion_3depth_name());
        addressList.add(document.getRegion_4depth_name());

        for(int i = addressList.size()-1 ; i >=0 ; i--) {
            if (!addressList.get(i).isEmpty())
                return addressList.get(i);
        }

        return null;
    }

    // 요청 보내기 메서드
    private ResponseEntity<KakaoAddressResponse> sendGetRequest(String url, HttpHeaders headers) {
        headers.set("Content-Type", "application/json;charset=UTF-8");
        // HttpEntity에 헤더 추가
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // GET 요청 실행
        return restTemplate.exchange(
                url,                      // 요청 URL
                HttpMethod.GET,           // HTTP 메서드
                requestEntity,            // 요청 헤더 포함
                KakaoAddressResponse.class              // 응답 타입
        );

    }

}
