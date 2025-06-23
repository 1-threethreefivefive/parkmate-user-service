package com.parkmate.userservice.userfavorite.dto.response;

import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import com.parkmate.userservice.userfavorite.vo.response.UserFavoriteResponseVo;
import lombok.Builder;

public class UserFavoriteResponseDto {

    private String parkingLotUuid;

    @Builder
    private UserFavoriteResponseDto(String parkingLotUuid) {
        this.parkingLotUuid = parkingLotUuid;
    }

    public static UserFavoriteResponseDto from(UserFavorite userFavorite) {
        return UserFavoriteResponseDto.builder()
                .parkingLotUuid(userFavorite.getParkingLotUuid())
                .build();
    }

    public UserFavoriteResponseVo toVo() {
        return UserFavoriteResponseVo.builder()
                .parkingLotUuid(parkingLotUuid)
                .build();
    }

}
