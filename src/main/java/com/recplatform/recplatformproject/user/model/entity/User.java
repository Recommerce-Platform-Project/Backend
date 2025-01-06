package com.recplatform.recplatformproject.user.model.entity;

import com.recplatform.recplatformproject.user.model.enums.SocialType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(nullable = false, unique = true)
    @NotBlank(message = "값을 입력해야 합니다")
    private String userId;

    @Column(nullable = false)
    @NotBlank(message = "값을 입력해야 합니다")
    @Size(max = 255)
    private String userPassword;

    @Column(nullable = false)
    @NotBlank(message = "값을 입력해야 합니다")
    @Size(max = 50)
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "값을 입력해야 합니다")
    @Size(max = 50)
    private String userNickname;

    @Size(max = 50)
    private String userEmail;

    @Size(max = 50)
    private String userPhone;

    @Size(max = 50)
    private String address;

    @Size(max = 255)
    private String refreshToken;

    @Size(max = 50)
    private String profileImage;

    @Size(max = 50)
    private String socialEmail;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

}
