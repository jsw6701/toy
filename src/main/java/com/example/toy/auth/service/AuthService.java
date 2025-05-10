package com.example.toy.auth.service;

import com.example.toy.auth.dto.LoginRequestDto;
import com.example.toy.auth.dto.SignupRequestDto;
import com.example.toy.common.exception.CustomException;
import com.example.toy.common.exception.ErrorCode;
import com.example.toy.common.response.CreatedData;
import com.example.toy.common.security.jwt.JwtTokenProvider;
import com.example.toy.user.entity.User;
import com.example.toy.user.entity.UserRole;
import com.example.toy.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider tokenProvider;

  @Transactional
  public List<CreatedData> signup(SignupRequestDto signupRequestDto) {
    if (userRepository.existsByUsername(signupRequestDto.getUsername())) {
      throw new CustomException(ErrorCode.DUPLICATE_USER);
    }

    User user =
        User.builder()
            .username(signupRequestDto.getUsername())
            .password(passwordEncoder.encode(signupRequestDto.getPassword()))
            .role(UserRole.USER)
            .build();

    userRepository.save(user);

    List<CreatedData> createdData = new ArrayList<>();
    createdData.add(CreatedData.of(user.getId()));
    return createdData;
  }

  @Transactional
  public List<CreatedData> adminSignup(SignupRequestDto signupRequestDto) {
    if (userRepository.existsByUsername(signupRequestDto.getUsername())) {
      throw new CustomException(ErrorCode.DUPLICATE_USER);
    }

    User user =
        User.builder()
            .username(signupRequestDto.getUsername())
            .password(passwordEncoder.encode(signupRequestDto.getPassword()))
            .role(UserRole.ADMIN)
            .build();

    userRepository.save(user);

    List<CreatedData> createdData = new ArrayList<>();
    createdData.add(CreatedData.of(user.getId()));
    return createdData;
  }

  @Transactional
  public String login(LoginRequestDto loginRequestDto) {
    // 인증 토큰 생성
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            loginRequestDto.getUsername(), loginRequestDto.getPassword());

    // 실제 인증 (UserDetailsService의 loadUserByUsername 메소드가 실행됨)
    Authentication authentication =
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);

    // JWT 토큰 생성
    return tokenProvider.createToken(authentication);
  }
}
