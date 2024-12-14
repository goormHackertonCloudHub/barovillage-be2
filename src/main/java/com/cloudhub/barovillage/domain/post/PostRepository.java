package com.cloudhub.barovillage.domain.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Long>{
    // TODO: getPosts 구현해야함.
    // List<Post> findAllByTransactionTypeAndSiAndGuAndDongList(String transaction_type, String si, String gu, String dong);

    // @Query("SELECT p FROM Post p JOIN User u ON p.user.id = uid WHERE p.transaction_type = t AND p.si = u.si AND p.gu = u.gu AND p.dong = u.dong")
    @Query("SELECT p FROM Post p")
    List<Post> findByTransactionTypeList(@Param("t") String transactionType, @Param("uid") String userId);
} 
