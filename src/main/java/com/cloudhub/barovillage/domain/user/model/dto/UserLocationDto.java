package com.cloudhub.barovillage.domain.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLocationDto {
    private String code;
    private String lastAddressName;
}
