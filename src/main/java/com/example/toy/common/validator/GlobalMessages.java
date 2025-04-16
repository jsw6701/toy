package com.example.toy.common.validator;

import java.util.Arrays;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GlobalMessages {
  private final MessageSource messageSource;

  /**
   * Label, Message 조회
   *
   * @param code: Label, Message Code
   * @return Label, Message
   */
  public String getMessage(String code) {
    return getMessage(code, null);
  }

  public String getMessageWithLabels(String messageCode, String[] labelCodeArgs) {
    Locale locale = LocaleContextHolder.getLocale();
    String[] labelArr =
        Arrays.stream(labelCodeArgs)
            .map(arg -> messageSource.getMessage(arg, null, locale))
            .toArray(String[]::new);
    return getMessage(messageCode, labelArr);
  }

  public String getMessage(String code, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(code, args, locale);
  }
}
