package com.parkmate.userservice.users.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetNameResponseVo {

    private String name;

    @Builder
    private UserGetNameResponseVo(String name) {
        this.name = name;
    }

}
