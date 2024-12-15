package com.cloudhub.barovillage.domain.comment.model.dto;

import com.cloudhub.barovillage.domain.comment.Comment;
import com.cloudhub.barovillage.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class CommentDTO {
    private final String username;
    private final String userProfileImageUrl;
    private final String content;

    public static CommentDTO fromComment(Comment comment){
        User commentUser = comment.getUser();
        return CommentDTO.builder()
                .username(commentUser.getNickname())
                .userProfileImageUrl(commentUser.getProfileImageUrl())
                .content(comment.getContent())
                .build();
    }
}
