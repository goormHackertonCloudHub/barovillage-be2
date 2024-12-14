package com.cloudhub.barovillage.domain.comment.model.response;

import com.cloudhub.barovillage.domain.comment.Comment;
import com.cloudhub.barovillage.domain.comment.model.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommentsResDTO {
    List<CommentDTO> commentList;

    CommentsResDTO(List<CommentDTO> commentList){
        this.commentList = commentList;
    }

    public static CommentsResDTO fromCommentList(List<Comment> comments){
        List<CommentDTO> results = new ArrayList<>();
        comments.forEach((c)->{
            results.add(CommentDTO.fromComment(c));
        });
        return new CommentsResDTO(results);
    }
}
