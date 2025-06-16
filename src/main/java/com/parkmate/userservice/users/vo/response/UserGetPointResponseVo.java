package com.parkmate.userservice.users.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetPointResponseVo {

    private int point;

    @Builder
    private UserGetPointResponseVo(int point) {
        this.point = point;
    }

}
