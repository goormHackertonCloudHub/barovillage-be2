package com.cloudhub.barovillage.domain.post.model.request;

import com.cloudhub.barovillage.domain.post.Post;
import com.cloudhub.barovillage.domain.user.User;
import jakarta.annotation.Nullable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddPostRequestDTO {
    private String title;
    private String content;
//    private String imageUrl;
    private String postType;
//    private String status;
    @Nullable
    private User user;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
//                .imageUrl(imageUrl)
                .postType(postType)
                .status("ING")
//                .user(user)
                .build();
    }
}
