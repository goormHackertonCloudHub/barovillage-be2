package com.cloudhub.barovillage.domain.comment;

import com.cloudhub.barovillage.domain.comment.model.request.AddCommentRequestDTO;
import com.cloudhub.barovillage.domain.comment.model.response.AddCommentResponseDTO;
import com.cloudhub.barovillage.domain.comment.model.response.CommentsResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/posts/{post_id}/comments")
    public CommentsResDTO getCommentList(@PathVariable("post_id")Long postId, @RequestHeader("Authorization") String userId){
        CommentsResDTO responseDTO = commentService.getComments(postId);
        return responseDTO;
    }

    @PostMapping("posts/{post_id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable(name = "post_id") String postId, @RequestBody AddCommentRequestDTO requestDTO, @RequestHeader("Authorization") String userId){
        Comment commentPS = commentService.addComment(postId, requestDTO, userId);
        return ResponseEntity.ok(commentPS);
    }
}
