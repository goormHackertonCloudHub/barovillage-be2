package com.cloudhub.barovillage.domain.post;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@Table(name = "post_table")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    @Column()
    private String imageUrl;
    private String transactionType;

    @CreatedDate
    private LocalDateTime createAt;
    private String status;

    @Builder
    public Post(Integer id, String title, String content, String imageUrl, String transactionType, LocalDateTime createAt, String status){
        this.id = id;
        this.title=title;
        this.content = content;
        this.imageUrl= imageUrl;
        this.transactionType=transactionType;
        this.createAt=createAt;
        this.status=status;
    }
}
