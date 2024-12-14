package com.cloudhub.barovillage.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    // TODO: Request DTO 구현해야함.
    
} 
