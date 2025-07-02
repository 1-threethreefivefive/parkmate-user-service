package com.parkmate.userservice.userfavorite.dto.request;

import com.parkmate.userservice.userfavorite.vo.request.UserFavoriteGetIsFavoriteRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteGetIsFavoriteRequestDto {

    private String userUuid;
    private String parkingLotUuid;

    @Builder
    private UserFavoriteGetIsFavoriteRequestDto(String userUuid,
                                                String parkingLotUuid) {
        this.userUuid = userUuid;
        this.parkingLotUuid = parkingLotUuid;
    }


    public static UserFavoriteGetIsFavoriteRequestDto of(String userUuid, UserFavoriteGetIsFavoriteRequestVo userFavoriteGetIsFavoriteRequestVo) {
        return UserFavoriteGetIsFavoriteRequestDto.builder()
                .userUuid(userUuid)
                .parkingLotUuid(userFavoriteGetIsFavoriteRequestVo.getParkingLotUuid())
                .build();
    }

}
