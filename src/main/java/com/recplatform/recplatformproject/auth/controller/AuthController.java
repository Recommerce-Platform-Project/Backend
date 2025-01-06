package com.recplatform.recplatformproject.auth.controller;

import com.recplatform.recplatformproject.auth.model.dto.request.AuthRequestDTO;
import com.recplatform.recplatformproject.auth.model.dto.response.AuthResponseDTO;
import com.recplatform.recplatformproject.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO requestDto) {
        AuthResponseDTO responseDto = this.authService.login(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
