package com.example.toy.common.response.model;

import com.example.toy.common.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  @Schema(
      description = "에러 코드",
      example = "BAD_REQUEST",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String code;

  @Schema(
      description = "에러 메시지",
      example = "Bad request.",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String message;

  @JsonIgnore private int status;

  @Schema(
      description = "응답 타임스탬프",
      example = "1717128250126",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private long timestamp;

  @Schema(description = "필드별 에러 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
  private List<FieldError> errors;

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FieldError {
    @Schema(description = "필드명", example = "title", requiredMode = Schema.RequiredMode.REQUIRED)
    private String field;

    @Schema(description = "값", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

    @Schema(
        description = "메시지",
        example = "제목은(는) 빈 문자열이 입력될 수 없습니다.",
        requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;

    public FieldError(String field, String value, String message) {
      this.field = field;
      this.value = value;
      this.message = message;
    }

    public FieldError(FieldError fieldError) {
      this.field = fieldError.getField();
      this.value = fieldError.getValue();
      this.message = fieldError.getMessage();
    }
  }

  private void setErrorCode(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.status = errorCode.getStatus().value();
    this.timestamp = System.currentTimeMillis();
  }

  private ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
    setErrorCode(errorCode);
    this.errors = errors.stream().map(FieldError::new).toList();
  }

  private ErrorResponse(ErrorCode errorCode, String errMessage) {
    setErrorCode(errorCode);
    this.errors = List.of(new FieldError("", "", errMessage));
  }

  private ErrorResponse(ErrorCode errorCode, String field, String value, String errMessage) {
    setErrorCode(errorCode);
    this.errors = List.of(new FieldError(field, value, errMessage));
  }

  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(errorCode, Collections.emptyList());
  }

  public static ErrorResponse of(ErrorCode errorCode, String errMessage) {
    return new ErrorResponse(errorCode, errMessage);
  }

  public static ErrorResponse of(
      ErrorCode errorCode, String field, Object rejectValue, String errMessage) {
    return new ErrorResponse(errorCode, field, String.valueOf(rejectValue), errMessage);
  }

  public String toJsonString() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(this);
  }
}
