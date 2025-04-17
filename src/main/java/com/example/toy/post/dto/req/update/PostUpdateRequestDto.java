package com.example.toy.post.dto.req.update;

import com.example.toy.common.base.BaseValidate;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostUpdateRequestDto implements IPostUpdateRequestDto, BaseValidate {
  @Schema(description = "게시글 ID", example = "1")
  private Long postId;

  @Schema(description = "제목", example = "제목")
  private String title;

  @Schema(description = "내용", example = "내용")
  private String content;

  @Override
  public void validate() {
    Map<String, Object> targetFields = new HashMap<>();
    targetFields.put("label.post.id", postId);
    targetFields.put("label.post.title", title);
    targetFields.put("label.post.content", content);
    BaseValidate.super.validateRequiredParam(targetFields);
  }
}
