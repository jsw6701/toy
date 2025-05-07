package com.example.toy.auth.controller;

import com.example.toy.auth.dto.LoginRequestDto;
import com.example.toy.auth.dto.LoginResponseDto;
import com.example.toy.auth.dto.SignupRequestDto;
import com.example.toy.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSwagger {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
    authService.signup(signupRequestDto);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(
      @Valid @RequestBody LoginRequestDto loginRequestDto) {
    String token = authService.login(loginRequestDto);
    return ResponseEntity.ok(new LoginResponseDto(token));
  }
}
