package com.cloudhub.barovillage.domain.post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudhub.barovillage.domain.post.model.request.RequestDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO.GetPostsResDTO;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController{
    private final PostService postService;
    private final String Authorization = "Authorization";

    @GetMapping("/posts")
    public GetPostsResDTO getPosts(@RequestParam RequestDTO.GetPostsReqDTO params, @RequestHeader Map<String,String> header) {
        System.out.println(params.toString());
        final String userId = header.get(Authorization);
        GetPostsResDTO responseDTO = postService.getPosts(params, userId);
        return responseDTO;
    }
    
    
}