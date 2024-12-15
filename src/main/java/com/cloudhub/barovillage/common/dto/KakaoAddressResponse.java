package com.cloudhub.barovillage.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true) // ??
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAddressResponse {
    private Meta meta;                    // meta 필드
    private List<Document> documents;     // documents 배열

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {            // meta 객체
        private int total_count;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Document {        // documents 배열의 각 객체
        private String region_type;
        private String code;
        private String address_name;
        private String region_1depth_name;
        private String region_2depth_name;
        private String region_3depth_name;
        private String region_4depth_name;
    }
}
