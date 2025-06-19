package com.parkmate.userservice.users.dto.response;

import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.vo.response.UserGetNameResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetNameResponseDto {

    private String name;

    @Builder
    private UserGetNameResponseDto(String name) {
        this.name = name;
    }

    public static UserGetNameResponseDto from(User user) {
        return UserGetNameResponseDto.builder()
                .name(user.getName())
                .build();
    }

    public UserGetNameResponseVo toVo() {
        return UserGetNameResponseVo.builder()
                .name(name)
                .build();
    }

}