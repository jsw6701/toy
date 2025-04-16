package com.example.toy.common.base;

import com.example.toy.common.exception.ApiBizException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Map;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public interface BaseValidate {
  void validate();

  default void validateRequiredParam(Map<String, Object> targetFields) {
    targetFields.forEach(
        (key, value) -> {
          if (value instanceof String) {
            if (!StringUtils.hasText(value.toString())) {
              throw new ApiBizException("msg.common.param.add.empty", key);
            }
          } else {
            if (ObjectUtils.isEmpty(value)) {
              throw new ApiBizException("msg.common.param.add.empty", key);
            }
          }
        });
  }

  default boolean isNotValidYearMonthRange(YearMonth startYearMonth, YearMonth endYearMonth) {
    if (ObjectUtils.isEmpty(startYearMonth) || ObjectUtils.isEmpty(endYearMonth)) {
      return true;
    }
    return endYearMonth.isBefore(startYearMonth);
  }

  default boolean isNotValidDateRange(LocalDate startDate, LocalDate endDate) {
    if (startDate == null || endDate == null) {
      return true;
    }

    return endDate.isBefore(startDate);
  }

  default boolean isNotValidDateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
    if (startDateTime == null || endDateTime == null) {
      return true;
    }

    return endDateTime.isBefore(startDateTime);
  }
}
