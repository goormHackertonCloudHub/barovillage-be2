package com.cloudhub.barovillage.domain.post;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cloudhub.barovillage.domain.post.model.request.RequestDTO.GetPostsReqDTO;
import com.cloudhub.barovillage.domain.post.model.response.ResponseDTO.GetPostsResDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
        private final PostRepository postRepository;
    
        public GetPostsResDTO getPosts(GetPostsReqDTO requestDTO, String userId){
            List<Post> posts = postRepository.findByTransactionTypeList(requestDTO.getTransaction_type(), userId);
            GetPostsResDTO responseDTO = GetPostsResDTO.fromPostList(posts);
            return responseDTO;
        }
}
