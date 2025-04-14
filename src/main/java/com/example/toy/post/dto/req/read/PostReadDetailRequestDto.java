package com.example.toy.post.dto.req.read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostReadDetailRequestDto implements IPostReadDetailRequestDto {
    private Long postId;
}
