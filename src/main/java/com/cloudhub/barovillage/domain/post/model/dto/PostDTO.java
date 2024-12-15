package com.cloudhub.barovillage.domain.post.model.dto;

import java.time.LocalDateTime;

import com.cloudhub.barovillage.domain.post.Post;

import com.cloudhub.barovillage.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private final Long postId;
    private final String imageUrl;
    private final String title;
    private final LocalDateTime createAt;
    private final Long userId;
    private final String status;
    private final String username;

    public PostDTO(Post post) {
//        System.out.println("postDTO inside");
        this.postId = post.getId();
        this.imageUrl = post.getImageUrl();
        this.title = post.getTitle();
        this.createAt = post.getCreateAt();
        this.status = post.getStatus();
        User user = post.getUser();
        this.userId = user.getId();
        this.username = user.getNickname();
    }
}
