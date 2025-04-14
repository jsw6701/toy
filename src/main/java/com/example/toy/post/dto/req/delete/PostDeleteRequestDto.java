package com.example.toy.post.dto.req.delete;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDeleteRequestDto implements IPostDeleteRequestDto {
    @NotEmpty(message = "게시글 ID는 필수입니다.")
    private Long postId;
}
