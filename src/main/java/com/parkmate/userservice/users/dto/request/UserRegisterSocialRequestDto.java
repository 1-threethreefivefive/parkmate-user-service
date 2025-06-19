package com.parkmate.userservice.users.dto.request;

import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.vo.request.UserRegisterSocialRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterSocialRequestDto {

    private String userUuid;
    private String name;

    @Builder
    private UserRegisterSocialRequestDto(String userUuid, String name) {
        this.userUuid = userUuid;
        this.name = name;
    }

    public static UserRegisterSocialRequestDto from(UserRegisterSocialRequestVo userRegisterSocialRequestVo) {
        return UserRegisterSocialRequestDto.builder()
                .userUuid(userRegisterSocialRequestVo.getUserUuid())
                .name(userRegisterSocialRequestVo.getName())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userUuid(userUuid)
                .name(name)
                .build();
    }

}