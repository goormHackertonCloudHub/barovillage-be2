package com.cloudhub.barovillage.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

     @Query("SELECT c FROM Comment c JOIN c.post p ON p.id = :pi")
     List<Comment> findAllById(@Param("pi") Long postId);
}
