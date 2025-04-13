package com.example.toy.post.dto.req;

import com.example.toy.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto implements IPostRequestDto {
    private String title;
    private String content;

    public Post toEntity() {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return post;
    }
}