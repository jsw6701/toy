package com.example.toy.post.dto.res;

import com.example.toy.post.entity.Post;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
  private Long id;
  private String title;
  private String content;
  private String creatorCd;
  private LocalDateTime createdAt;
  private String updaterCd;
  private LocalDateTime updatedAt;
  private String isDeleted;

  public static PostResponseDto fromEntity(Post post) {
    return PostResponseDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .creatorCd(post.getCreatorCd())
        .createdAt(post.getCreatedAt())
        .updaterCd(post.getUpdaterCd())
        .updatedAt(post.getUpdatedAt())
        .isDeleted(post.getIsDeleted())
        .build();
  }
}
