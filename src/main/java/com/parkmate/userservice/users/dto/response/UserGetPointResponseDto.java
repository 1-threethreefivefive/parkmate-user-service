package com.parkmate.userservice.users.dto.response;

import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.vo.response.UserGetPointResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetPointResponseDto {

    private int point;

    @Builder
    private UserGetPointResponseDto(int point) {
        this.point = point;
    }

    public static UserGetPointResponseDto from(User user) {
        return UserGetPointResponseDto.builder()
                .point(user.getPoint())
                .build();
    }

    public UserGetPointResponseVo toVo() {
        return UserGetPointResponseVo.builder()
                .point(point)
                .build();
    }

}