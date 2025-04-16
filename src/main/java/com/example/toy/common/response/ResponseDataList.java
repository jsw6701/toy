package com.example.toy.common.response;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ResponseDataList<T> {
  private final List<T> resultList;
}
