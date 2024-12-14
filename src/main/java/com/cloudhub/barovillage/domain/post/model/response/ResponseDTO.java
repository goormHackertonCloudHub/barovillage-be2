package com.cloudhub.barovillage.domain.post.model.response;

import java.util.ArrayList;
import java.util.List;

import com.cloudhub.barovillage.domain.post.Post;
import com.cloudhub.barovillage.domain.post.model.dto.PostDTO;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class ResponseDTO{

    
    @RequiredArgsConstructor
    public static class GetPostsResDTO{
        private final List<PostDTO> postDTOList;

        public static GetPostsResDTO fromPostList(List<Post> posts){
            List<PostDTO> results = new ArrayList<>();
            posts.forEach((p)->{
                results.add(new PostDTO(p));
            });
            return new GetPostsResDTO(results);
        }
    }
}