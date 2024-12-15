package com.cloudhub.barovillage.domain.comment;

import com.cloudhub.barovillage.domain.comment.model.request.AddCommentRequestDTO;
import com.cloudhub.barovillage.domain.comment.model.response.AddCommentResponseDTO;
import com.cloudhub.barovillage.domain.comment.model.response.CommentsResDTO;
import com.cloudhub.barovillage.domain.post.Post;
import com.cloudhub.barovillage.domain.post.PostRepository;
import com.cloudhub.barovillage.domain.user.User;
import com.cloudhub.barovillage.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentsResDTO getComments(Long postId) {
        List<Comment> commentList = commentRepository.findAllById(postId);
        return CommentsResDTO.fromCommentList(commentList);
    }

    public Comment addComment(String postId, AddCommentRequestDTO requestDTO, String userId) {
        Optional<Post> optionalPost = postRepository.findById(Long.parseLong(postId));
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId));

        Comment comment = requestDTO.toEntity();
        optionalPost.ifPresent(comment::setPost);
        optionalUser.ifPresent(comment::setUser);

        Comment commentPS = commentRepository.save(comment);
        return commentPS;
    }
}
