package com.recplatform.recplatformproject.auth.config;

import com.recplatform.recplatformproject.user.model.entity.Users;
import com.recplatform.recplatformproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// CustomUserDetailService 에서 UserRepository 를 통해 User 를 조회하고
// Authority(인가)가 추가된 CustomUserDetails 객체를 반환
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    //UserName으로 조회
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = userRepository.findByUserName(userName).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다. userName =" + userName));
        return new CustomUserDetails(users);
    }

    //UserId로 조회
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        Users users = userRepository.findByUserName(userId).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다. userId =" + userId));
        return new CustomUserDetails(users);
    }
}
