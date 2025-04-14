package com.example.toy.post.dto.req.update;

import com.example.toy.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostUpdateRequestDto implements IPostUpdateRequestDto {
    private Long postId;
    private String title;
    private String content;

    public Post toEntity() {
        Post post = new Post();
        post.setId(postId);
        post.setTitle(title);
        post.setContent(content);
        return post;
    }
}