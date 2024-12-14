package com.cloudhub.barovillage.domain.post.model.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cloudhub.barovillage.domain.post.Post;
import com.cloudhub.barovillage.domain.post.model.dto.PostDTO;

import com.cloudhub.barovillage.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ResponseDTO{

    
    @RequiredArgsConstructor
    @Getter
    @Setter
    public static class GetPostsResDTO{
        private final List<PostDTO> postList;

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

    @Getter
    @Setter
    public static class GetPostDetailResDTO{
        private final String postId;
        private final String title;
        private final String postType;
        private final String content;
        private final String imageUrl;
        private final LocalDateTime createAt;
        private final String status;
        private final Long userId;
        private final String username;
        private final String profileImageUrl;

        @Builder
        public GetPostDetailResDTO(String postId, String title, String postType, String content, String imageUrl,
                LocalDateTime createAt, String status, Long userId, String username, String profileImageUrl) {
            this.postId = postId;
            this.title = title;
            this.postType = postType;
            this.content = content;
            this.imageUrl = imageUrl;
            this.createAt = createAt;
            this.status = status;
            this.userId = userId;
            this.username = username;
            this.profileImageUrl = profileImageUrl;
        }

        public GetPostDetailResDTO(Post post){
            this.postId = String.valueOf(post.getId());
            this.title = post.getTitle();
            this.postType = post.getPostType();
            this.content = post.getContent();
            this.imageUrl = post.getImageUrl();
            this.createAt = post.getCreateAt();
            this.status = post.getStatus();
            User user = post.getUser();
            this.userId = user.getId();
            this.username = user.getNickname();
            this.profileImageUrl = user.getProfileImageUrl();
        }
    }
}