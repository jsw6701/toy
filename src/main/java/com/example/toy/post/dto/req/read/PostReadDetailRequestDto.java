package com.example.toy.post.dto.req.read;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostReadDetailRequestDto implements IPostReadDetailRequestDto {
  @NotEmpty(message = "게시글 ID는 필수입니다.")
  private Long postId;
}
