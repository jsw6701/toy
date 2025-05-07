package com.example.toy.common.response;

import com.example.toy.common.provider.ApplicationContextProvider;
import com.example.toy.common.validator.GlobalMessages;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtils {

  private ResponseUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> ResponseData<T> data(T data) {
    if (ObjectUtils.isEmpty(data)) {
      return ResponseData.of(getEmptyObject(data));
    }
    return ResponseData.of(data);
  }

  public static <T> ResponseDataList<T> dataList(List<T> dtoList) {
    if (ObjectUtils.isEmpty(dtoList)) {
      return ResponseDataList.of(getEmptyObject(dtoList));
    }
    return ResponseDataList.of(dtoList);
  }

  public static <T> ResponseData<Map<String, String>> message(String messageKey, String labelCode) {
    final GlobalMessages globalMessages =
        ApplicationContextProvider.getApplicationContext().getBean(GlobalMessages.class);

    String message = globalMessages.getMessage(messageKey);
    if (StringUtils.hasText(labelCode)) {
      String labelMessage = globalMessages.getMessage(labelCode);
      message = globalMessages.getMessage(messageKey, new String[] {labelMessage});
    }

    return ResponseUtils.data(Map.of("message", message));
  }

  @SuppressWarnings("unchecked")
  private static <T> T getEmptyObject(T data) {
    if (data instanceof List) {
      return (T) new ArrayList<>();
    } else if (data instanceof Map) {
      return (T) new HashMap<>();
    } else {
      return null;
    }
  }
}
