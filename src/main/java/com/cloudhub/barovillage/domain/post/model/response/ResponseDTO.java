package com.cloudhub.barovillage.domain.post.model.response;

import java.util.ArrayList;
import java.util.List;

import com.cloudhub.barovillage.domain.post.Post;
import com.cloudhub.barovillage.domain.post.model.dto.PostDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ResponseDTO{

    
    @RequiredArgsConstructor
    @Getter
    @Setter
    public static class GetPostsResDTO{
        private final List<PostDTO> postDTOList;

        public static GetPostsResDTO fromPostList(List<Post> posts){
            System.out.println("inside\n");
            List<PostDTO> results = new ArrayList<>();
            posts.forEach((p)->{
                // System.out.println(p.toString());
                results.add(new PostDTO(p));
            });
            return new GetPostsResDTO(results);
        }
    }
}