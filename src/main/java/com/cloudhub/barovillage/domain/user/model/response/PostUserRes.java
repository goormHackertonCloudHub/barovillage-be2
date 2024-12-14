package com.cloudhub.barovillage.domain.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserRes {
    private Long userId;
    private String nickname;
}
