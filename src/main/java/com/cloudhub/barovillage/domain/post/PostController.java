package com.cloudhub.barovillage.domain.post;

import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import com.cloudhub.barovillage.domain.post.model.request.RequestDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO.GetPostsResDTO;

import lombok.RequiredArgsConstructor;

import java.net.http.HttpHeaders;
import java.util.Map;

import org.springframework.messaging.handler.annotation.Header;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostController{
    private final PostService postService;


    @GetMapping("/posts")
    @ResponseBody
    public GetPostsResDTO getPosts(@RequestParam(name = "post_type") String tT, @RequestHeader("Authorization") String userId) {
        System.out.println(tT);
        System.out.println(userId);
        GetPostsResDTO responseDTO = postService.getPosts(tT, userId);
        System.out.println(responseDTO.toString());
        return responseDTO;
    }

    @ResponseBody
    @GetMapping("/posts/{post_id}")
    public ResponseDTO.GetPostDetailResDTO getMethodName(@PathVariable("post_id") Long postId) {
        ResponseDTO.GetPostDetailResDTO responseDTO =  postService.getPostDetailResDTO(postId);
        return responseDTO;
    }
    
    
    @PostMapping("/add/post")
    public String addPost(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }


    
}