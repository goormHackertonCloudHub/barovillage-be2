package com.cloudhub.barovillage.domain.post.model.dto;

import java.time.LocalDateTime;

import com.cloudhub.barovillage.domain.post.Post;

public class PostDTO {
    private Integer postID;
    private String imageUrl;
    private String title;
    private LocalDateTime createAt;

    public PostDTO(Post post) {
        this.postID = post.getId();
        this.imageUrl = post.getImageUrl();
        this.title = post.getTitle();
        this.createAt = post.getCreateAt();
    }
}
