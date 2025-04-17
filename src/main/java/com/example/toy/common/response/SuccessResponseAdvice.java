package com.example.toy.common.response;

import com.example.toy.common.response.model.SuccessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = {"com.example.toy"})
public class SuccessResponseAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(
      MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      org.springframework.http.MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      org.springframework.http.server.ServerHttpRequest request,
      org.springframework.http.server.ServerHttpResponse response) {

    if (body instanceof ResponseDataList<?> || body instanceof ResponseData<?>) {
      return SuccessResponse.of(body);
    }

    return body;
  }
}
