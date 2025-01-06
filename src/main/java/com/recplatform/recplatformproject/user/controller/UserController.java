package com.recplatform.recplatformproject.user.controller;

import com.recplatform.recplatformproject.auth.config.JwtTokenProvider;
import com.recplatform.recplatformproject.user.model.dto.response.UserResponseDTO;
import com.recplatform.recplatformproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/api/user")
    public ResponseEntity<UserResponseDTO> findUser(@RequestHeader("Authorization") String accessToken){
        String userId = jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        UserResponseDTO userResponseDTO = userService.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }
}
