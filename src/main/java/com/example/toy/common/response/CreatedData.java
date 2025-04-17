package com.example.toy.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedData {
  @Schema(description = "생성된 데이터 ID", example = "1")
  Long id;

  public static CreatedData of(Long id) {
    return new CreatedData(id);
  }
}
