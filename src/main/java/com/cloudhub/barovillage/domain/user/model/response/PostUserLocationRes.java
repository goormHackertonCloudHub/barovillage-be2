package com.cloudhub.barovillage.domain.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUserLocationRes {
    private Long userId;
    private String regionName;
    private Integer regionCode;
}
