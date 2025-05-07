package com.example.toy.auth.controller;

import com.example.toy.auth.dto.LoginRequestDto;
import com.example.toy.auth.dto.LoginResponseDto;
import com.example.toy.auth.dto.SignupRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthSwagger {

  @Operation(summary = "회원가입", description = "회원가입 API입니다.")
  ResponseEntity<Void> signup(@Valid @RequestBody SignupRequestDto signupRequestDto);

  @Operation(summary = "로그인", description = "로그인 API입니다.")
  ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto);
}
