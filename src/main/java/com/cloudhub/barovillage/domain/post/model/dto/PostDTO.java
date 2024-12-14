package com.cloudhub.barovillage.domain.post.model.dto;

import java.time.LocalDateTime;

import com.cloudhub.barovillage.domain.post.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Integer postID;
    private String imageUrl;
    private String title;
    private LocalDateTime createAt;

    public PostDTO(Post post) {
        System.out.println("postDTO inside");
        this.postID = post.getId();
        this.imageUrl = post.getImageUrl();
        this.title = post.getTitle();
        this.createAt = post.getCreateAt();
    }
}
