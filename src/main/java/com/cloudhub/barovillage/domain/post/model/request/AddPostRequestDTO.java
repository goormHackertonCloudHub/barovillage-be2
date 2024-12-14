package com.cloudhub.barovillage.domain.post.model.request;

import com.cloudhub.barovillage.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddPostRequestDTO {
    private String title;
    private String content;
    private String imageUrl;
    private String postType;
    private String status;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .postType(postType)
                .status(status)
                .build();
    }
}
