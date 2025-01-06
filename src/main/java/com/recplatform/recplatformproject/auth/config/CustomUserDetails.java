package com.recplatform.recplatformproject.auth.config;

import com.recplatform.recplatformproject.user.model.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final Users users;

    public CustomUserDetails(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    ///////////////////////////////////////////////////
    public String getUserID() {
        return users.getUserId();
    }

    public String getUserNickname() {
        return users.getUserNickname();
    }

    public String getUserEmail() {
        return users.getUserEmail();
    }

    public String getUserPhone() {
        return users.getUserPhone();
    }

    public String getAddress() {
        return users.getAddress();
    }

    public String getProfileImage() {
        return users.getProfileImage();
    }

    public String getSocialEmail() {
        return users.getProfileImage();
    }

    public String getSocialType() {
        return users.getSocialType().name();
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
