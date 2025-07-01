package com.parkmate.userservice.userfavorite.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteGetResponseVo {

    private String parkingLotUuid;

    @Builder
    private UserFavoriteGetResponseVo(String parkingLotUuid) {
        this.parkingLotUuid = parkingLotUuid;
    }

}
