package com.example.toy.post.dto.req.read;

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
public class PostReadDetailRequestDto implements IPostReadDetailRequestDto, BaseValidate {

  @Schema(description = "게시글 ID", example = "1")
  private Long postId;

  @Override
  public void validate() {
    Map<String, Object> targetFields = new HashMap<>();
    targetFields.put("label.post.id", postId);
    BaseValidate.super.validateRequiredParam(targetFields);
  }
}
