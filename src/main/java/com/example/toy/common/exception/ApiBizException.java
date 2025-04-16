package com.example.toy.common.exception;

import com.example.toy.common.provider.ApplicationContextProvider;
import com.example.toy.common.validator.GlobalMessages;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.Getter;

@SuppressWarnings("serial")
public class ApiBizException extends RuntimeException {

  private static final String LABEL_KEY_FORMAT = "label.";
  private static final String MESSAGE_KEY_FORMAT = "msg.";

  @Getter private String field;
  @Getter private String value;

  public ApiBizException(String message) {
    super(formatMessage(message));
  }

  public ApiBizException(String message, Throwable cause) {
    super(formatMessage(message), cause);
  }

  public ApiBizException(String messageKey, String... labelKeysOrValues) {
    super(getValidMessage(messageKey, labelKeysOrValues));
  }

  public ApiBizException(String message, String field, String value) {
    super(formatMessage(message));
    this.field = field;
    this.value = value;
  }

  private static String formatMessage(String message) {
    if (message.contains(MESSAGE_KEY_FORMAT)) {
      GlobalMessages globalMessage = getGlobalMessage();
      return globalMessage.getMessage(message);
    }
    return message;
  }

  private static String getValidMessage(String messageKey, String... labelKeysOrValues) {
    GlobalMessages globalMessage = getGlobalMessage();
    String validMessage = globalMessage.getMessage(messageKey);

    if (labelKeysOrValues != null && labelKeysOrValues.length > 0) {
      String labelTexts =
          Arrays.stream(labelKeysOrValues)
              .map(key -> key.contains(LABEL_KEY_FORMAT) ? globalMessage.getMessage(key) : key)
              .collect(Collectors.joining(", "));
      validMessage = globalMessage.getMessage(messageKey, labelTexts.split(","));
    }
    return validMessage;
  }

  private static GlobalMessages getGlobalMessage() {
    return ApplicationContextProvider.getApplicationContext().getBean(GlobalMessages.class);
  }
}
