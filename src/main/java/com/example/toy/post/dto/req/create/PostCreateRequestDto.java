package com.example.toy.post.dto.req.create;

import com.example.toy.common.base.BaseValidate;
import com.example.toy.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDto implements IPostCreateRequestDto, BaseValidate {

  @Schema(description = "제목", example = "제목")
  private String title;

  @Schema(description = "내용", example = "내용")
  private String content;

  public Post toEntity() {
    Post post = new Post();
    post.setTitle(title);
    post.setContent(content);
    return post;
  }

  @Override
  public void validate() {
    Map<String, Object> targetFields = new HashMap<>();
    targetFields.put("label.post.title", title);
    targetFields.put("label.post.content", content);
    BaseValidate.super.validateRequiredParam(targetFields);
  }
}
