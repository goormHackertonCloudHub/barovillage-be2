package com.cloudhub.barovillage.domain.post.model.request;

import lombok.Getter;
import lombok.Setter;

public class RequestDTO{

    @Getter
    @Setter
    public static class GetPostsReqDTO{
        private String transaction_type;
    } 
}