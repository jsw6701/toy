package com.example.toy.post.dto.req.delete;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDeleteRequestDto implements IPostDeleteRequestDto {
    private Long postId;
}
