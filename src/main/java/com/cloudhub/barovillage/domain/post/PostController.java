package com.cloudhub.barovillage.domain.post;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudhub.barovillage.domain.post.model.request.RequestDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO.GetPostsResDTO;

import lombok.RequiredArgsConstructor;

import java.net.http.HttpHeaders;
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
    @ResponseBody
    public GetPostsResDTO getPosts(@RequestParam(name = "transaction_type") String tT, @RequestHeader("Authorization") String userId) {
        System.out.println(tT);
        System.out.println(userId);
        GetPostsResDTO responseDTO = postService.getPosts(tT, userId);
        System.out.println(responseDTO.toString());
        return responseDTO;
    }
    
    
}