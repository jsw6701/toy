package com.example.toy.post.dto.req.update;

import com.example.toy.post.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostUpdateRequestDto implements IPostUpdateRequestDto {
    @NotEmpty(message = "게시글 ID는 필수입니다.")
    private Long postId;
    @NotEmpty(message = "제목은 필수입니다.")
    private String title;
    @NotEmpty(message = "내용은 필수입니다.")
    private String content;

    public Post toEntity() {
        Post post = new Post();
        post.setId(postId);
        post.setTitle(title);
        post.setContent(content);
        return post;
    }
}