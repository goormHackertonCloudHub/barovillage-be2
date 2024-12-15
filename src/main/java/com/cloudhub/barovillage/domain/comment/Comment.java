package com.cloudhub.barovillage.domain.comment;

import com.cloudhub.barovillage.domain.post.Post;
import com.cloudhub.barovillage.domain.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "comment_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String content;

    @CreatedDate
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn
    private Post post;

    @ManyToOne
    @JoinColumn
    private User user;
}
