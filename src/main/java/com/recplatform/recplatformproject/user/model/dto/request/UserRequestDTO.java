package com.recplatform.recplatformproject.user.model.dto.request;

import com.recplatform.recplatformproject.user.model.entity.User;
import com.recplatform.recplatformproject.user.model.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userPhone;
    private String profileImage;
    private String socialEmail;
    private SocialType socialType;

    //엔티티 생성하는 빌더
    public User toEntity(){
        return User.builder()
                .userId(this.userId)
                .userPassword(this.userPassword)
                .userName(this.userName)
                .userNickname(this.userNickname)
                .userEmail(this.userEmail)
                .userPhone(this.userPhone)
                .profileImage(this.profileImage)
                .socialEmail(this.socialEmail)
                .socialType(this.socialType)
                .build();
    }
}
