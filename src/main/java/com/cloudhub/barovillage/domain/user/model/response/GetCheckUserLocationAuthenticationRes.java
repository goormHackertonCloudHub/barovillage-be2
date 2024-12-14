package com.cloudhub.barovillage.domain.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCheckUserLocationAuthenticationRes {
    private Boolean userLocationAuthenticationStatus;
}
