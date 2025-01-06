package com.recplatform.recplatformproject.auth.config;

import com.recplatform.recplatformproject.user.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    ///////////////////////////////////////////////////
    public String getUserID() {
        return user.getUserId();
    }

    public String getUserNickname() {
        return user.getUserNickname();
    }

    public String getUserEmail() {
        return user.getUserEmail();
    }

    public String getUserPhone() {
        return user.getUserPhone();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getProfileImage() {
        return user.getProfileImage();
    }

    public String getSocialEmail() {
        return user.getProfileImage();
    }

    public String getSocialType() {
        return user.getSocialType().name();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    ////////////////////////////////////////////////////
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
