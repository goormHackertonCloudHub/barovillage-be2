package com.cloudhub.barovillage.domain.comment;

import com.cloudhub.barovillage.domain.comment.model.response.CommentsResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentsResDTO getComments(Long postId) {
        List<Comment> commentList = commentRepository.findAllById(postId);
        return CommentsResDTO.fromCommentList(commentList);
    }
}
