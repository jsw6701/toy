package com.example.toy.post.dto.req.create;

import com.example.toy.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostCreateRequestDto implements IPostCreateRequestDto {
    private String title;
    private String content;

    public Post toEntity() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return post;
    }
}