package com.cloudhub.barovillage.domain.comment.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AddCommentResponseDTO<T> {
    private final Integer code;
    private final String msg;
    private final T data;
}
