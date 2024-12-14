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
    
        public GetPostsResDTO getPosts(String transaction_type, String userId){
            
            List<Post> posts = postRepository.findByTransactionTypeList(transaction_type, Integer.parseInt(userId));
            posts.forEach((p)->{System.out.println(p.toString());});
            GetPostsResDTO responseDTO = GetPostsResDTO.fromPostList(posts);
            return responseDTO;
        }
}
