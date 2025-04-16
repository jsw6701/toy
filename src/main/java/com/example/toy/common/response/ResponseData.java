package com.example.toy.common.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ResponseData<T> {
  private final T result;
}
