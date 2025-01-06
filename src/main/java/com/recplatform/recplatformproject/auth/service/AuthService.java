package com.recplatform.recplatformproject.auth.service;

import com.recplatform.recplatformproject.auth.config.CustomUserDetails;
import com.recplatform.recplatformproject.auth.config.JwtTokenProvider;
import com.recplatform.recplatformproject.auth.model.dto.request.AuthRequestDTO;
import com.recplatform.recplatformproject.auth.model.dto.response.AuthResponseDTO;
import com.recplatform.recplatformproject.auth.model.entity.Auth;
import com.recplatform.recplatformproject.auth.repository.AuthRepository;
import com.recplatform.recplatformproject.user.model.entity.Users;
import com.recplatform.recplatformproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthResponseDTO login(AuthRequestDTO requestDTO) {
        Users users = userRepository.findByUserId(requestDTO.getUserId()).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다. userId =" + requestDTO.getUserId()));
        if (!passwordEncoder.matches(requestDTO.getUserPassword(), users.getUserPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. userId =" + requestDTO.getUserId());
        }

        //Access Token, Refresh Token 생성
        String accessToken = jwtTokenProvider.generateAccessToken(
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(users), users.getUserPassword()));
        String refreshToken = this.jwtTokenProvider.generateRefreshToken(
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(users), users.getUserPassword()));

        // AuthRepository 에 인증정보가 존재한다면 토큰을 갱신합니다.
        if (authRepository.existsByUsers(users)) {
            users.getAuth().setAccessToken(accessToken);
            users.getAuth().setRefreshToken(refreshToken);
            return new AuthResponseDTO(users.getAuth());
        }

        // AuthRepository 에 인증정보가 존재하지 않는다면 토큰을 저장합니다.
        Auth auth = authRepository.save(Auth.builder()
                .users(users)
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());

        return new AuthResponseDTO(auth);
    }
}






















