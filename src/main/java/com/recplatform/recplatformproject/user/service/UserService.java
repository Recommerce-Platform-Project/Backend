package com.recplatform.recplatformproject.user.service;

import com.recplatform.recplatformproject.user.model.dto.response.UserResponseDTO;
import com.recplatform.recplatformproject.user.model.entity.Users;
import com.recplatform.recplatformproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //UserId 로 User 조회
    public UserResponseDTO findByUserId(String userId) {
        Users users = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. userId =" + userId)
        );
        return new UserResponseDTO(users);
    }
}
