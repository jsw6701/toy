package com.example.toy.auth.controller;

import com.example.toy.auth.dto.LoginRequestDto;
import com.example.toy.auth.dto.LoginResponseDto;
import com.example.toy.auth.dto.SignupRequestDto;
import com.example.toy.common.response.CreatedData;
import com.example.toy.common.response.CreatedResponse;
import com.example.toy.common.response.DetailResponse;
import com.example.toy.common.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthSwagger {

  @Operation(summary = "회원가입", description = "회원가입 API입니다.")
  ResponseData<CreatedResponse<CreatedData>> signup(
      @Valid @RequestBody SignupRequestDto signupRequestDto);

  @Operation(summary = "로그인", description = "로그인 API입니다.")
  ResponseData<DetailResponse<LoginResponseDto>> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto);

  @Operation(summary = "관리자 회원가입", description = "관리자 회원가입 API입니다.")
  ResponseData<CreatedResponse<CreatedData>> adminSignup(
      @Valid @RequestBody SignupRequestDto signupRequestDto);
}
