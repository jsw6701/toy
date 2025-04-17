package com.example.toy.post.dto.req.read;

import com.example.toy.common.base.BaseValidate;
import com.example.toy.common.paging.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostReadAllRequestDto extends Paging implements IPostReadAllRequestDto, BaseValidate {

  @Schema(description = "삭제 여부", example = "N")
  private String isDeleted;

  @Override
  public void validate() {}
}
