package com.example.toy.common.response.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponse<T> {

  @Schema(description = "성공 메시지", example = "success", requiredMode = Schema.RequiredMode.REQUIRED)
  private String message;

  @Schema(
      description = "응답 타임스탬프",
      example = "1717128250126",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private long timestamp;

  @Schema(description = "응답 데이터", requiredMode = Schema.RequiredMode.REQUIRED)
  private T data;

  private void setSuccessResponse(String message) {
    this.message = message;
    this.timestamp = System.currentTimeMillis();
  }

  private SuccessResponse(T result, String message) {
    setSuccessResponse(message);
    this.data = result;
  }

  public static <T> SuccessResponse<T> of(T result) {
    return new SuccessResponse<>(result, "success");
  }

  public static boolean isSuccess(Object result) {
    return (result instanceof SuccessResponse<?>);
  }
}
