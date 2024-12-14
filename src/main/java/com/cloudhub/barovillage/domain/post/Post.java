package com.cloudhub.barovillage.domain.post;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import com.cloudhub.barovillage.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@Table(name = "post_table")
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String title;
    private  String content;

    @Column()
    private  String imageUrl;
    private  String postType;

    @CreatedDate
    private  LocalDateTime createAt;
    private  String status;

    private  Integer regionCode;

    @ManyToOne
    @JoinColumn
    private  User user;
}
