package com.parkmate.userservice.userfavorite.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteResponseVo {

    private String parkingLotUuid;

    @Builder
    private UserFavoriteResponseVo(String parkingLotUuid) {
        this.parkingLotUuid = parkingLotUuid;
    }
}
