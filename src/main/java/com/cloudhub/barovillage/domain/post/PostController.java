package com.cloudhub.barovillage.domain.post;

import com.cloudhub.barovillage.domain.post.model.request.AddPostRequestDTO;
import com.cloudhub.barovillage.domain.post.model.response.AddResponseDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    

    @ResponseBody
    @PostMapping("/posts/add")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequestDTO requestDTO, @RequestHeader("Authorization") HttpHeaders headers, BindingResult bindingResult) {
        //TODO: process POST request
        System.out.println("=================================");
        System.out.println(requestDTO.getTitle());
        
        Post postPS = postService.addPost(requestDTO);
        return ResponseEntity.ok(postPS);
    }


    
}