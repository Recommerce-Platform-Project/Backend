package com.recplatform.recplatformproject.user.model.dto.response;

import com.recplatform.recplatformproject.user.model.entity.Users;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userPhone;
    private String profileImage;
    private String socialEmail;
    private String socialType;

    @Builder
    public UserResponseDTO(Users entity) {
        this.userId = entity.getUserId();
        this.userPassword = entity.getUserPassword();
        this.userName = entity.getUserName();
        this.userNickname = entity.getUserNickname();
        this.userEmail = entity.getUserEmail();
        this.userPhone = entity.getUserPhone();
        this.profileImage = entity.getProfileImage();
        this.socialEmail = entity.getSocialEmail();
        this.socialType = entity.getSocialType().name();
    }
}
