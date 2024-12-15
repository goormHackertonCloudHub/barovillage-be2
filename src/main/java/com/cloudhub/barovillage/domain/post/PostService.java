package com.cloudhub.barovillage.domain.post;

import java.util.List;
import java.util.Optional;

import com.cloudhub.barovillage.domain.comment.Comment;
import com.cloudhub.barovillage.domain.comment.CommentRepository;
import com.cloudhub.barovillage.domain.post.model.request.AddPostRequestDTO;
import com.cloudhub.barovillage.domain.post.model.response.AddResponseDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO;
import com.cloudhub.barovillage.domain.user.User;
import com.cloudhub.barovillage.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cloudhub.barovillage.domain.post.model.request.RequestDTO.GetPostsReqDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO.GetPostsResDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
        private final PostRepository postRepository;
        private final CommentRepository commentRepository;


        @Transactional
        public Post addPost(AddPostRequestDTO requestDTO, Optional<User> optionalUser){
//        public Post addPost(AddPostRequestDTO requestDTO){
            try{
                requestDTO.setUser(optionalUser.get());
                Post postPS = postRepository.save(requestDTO.toEntity());
                return postPS;
            }catch (RuntimeException e){
                System.out.println("error: " + e.toString());
                return null;
            }

        }
    
        public GetPostsResDTO getPosts(String post_type, String userId){
            List<Post> posts = postRepository.findByPostTypeList(post_type);
//            List<Post>posts = postRepository.findByPostTypeList(post_type, userId);

            posts.forEach((p)->{
                System.out.println(p.getUser().getId());
            });

            GetPostsResDTO responseDTO = GetPostsResDTO.fromPostList(posts);
            return responseDTO;
        }

        public ResponseDTO.GetPostDetailResDTO getPostDetailResDTO(Long postId){
            Post post = postRepository.findAllByPostId(postId);
            List<Comment> commentList= commentRepository.findAllById(postId);
            ResponseDTO.GetPostDetailResDTO responseDTO = new ResponseDTO.GetPostDetailResDTO(post, commentList);
            return responseDTO;
        }
}
