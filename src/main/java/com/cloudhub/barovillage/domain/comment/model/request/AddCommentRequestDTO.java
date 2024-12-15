package com.cloudhub.barovillage.domain.comment.model.request;

import com.cloudhub.barovillage.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCommentRequestDTO {
    private String content;

    public Comment toEntity() {
        Comment comment =  new Comment();
        comment.setContent(content);
        return comment;
    }
}
