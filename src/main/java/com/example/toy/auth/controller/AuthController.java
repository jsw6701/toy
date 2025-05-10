package com.example.toy.auth.controller;

import com.example.toy.auth.dto.LoginRequestDto;
import com.example.toy.auth.dto.LoginResponseDto;
import com.example.toy.auth.dto.SignupRequestDto;
import com.example.toy.auth.service.AuthService;
import com.example.toy.common.response.*;
import com.example.toy.common.validator.GlobalMessages;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSwagger {

  private final GlobalMessages globalMessages;
  private final AuthService authService;
  private static final String LABEL_SIGNUP = "label.signup";

  @PostMapping("/signup")
  public ResponseData<CreatedResponse<CreatedData>> signup(
      @Valid @RequestBody SignupRequestDto signupRequestDto) {

    List<CreatedData> createdDataList = authService.signup(signupRequestDto);

    String message =
        globalMessages.getMessage(
            "msg.save.success.create", new String[] {globalMessages.getMessage(LABEL_SIGNUP)});

    return ResponseUtils.data(new CreatedResponse<>(message, createdDataList));
  }

  @PostMapping("/admin/signup")
  public ResponseData<CreatedResponse<CreatedData>> adminSignup(
      @Valid @RequestBody SignupRequestDto signupRequestDto) {

    List<CreatedData> createdDataList = authService.adminSignup(signupRequestDto);

    String message =
        globalMessages.getMessage(
            "msg.save.success.create", new String[] {globalMessages.getMessage(LABEL_SIGNUP)});

    return ResponseUtils.data(new CreatedResponse<>(message, createdDataList));
  }

  @PostMapping("/login")
  public ResponseData<DetailResponse<LoginResponseDto>> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto) {
    String token = authService.login(loginRequestDto);
    return ResponseUtils.data(new DetailResponse<>(new LoginResponseDto(token)));
  }
}
