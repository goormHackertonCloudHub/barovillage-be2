package com.cloudhub.barovillage.domain.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserLocationReq {
    private Double latitude;
    private Double longitude;

}
