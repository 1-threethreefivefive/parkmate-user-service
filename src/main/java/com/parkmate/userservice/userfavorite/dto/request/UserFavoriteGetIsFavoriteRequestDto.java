package com.parkmate.userservice.userfavorite.dto.request;

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


    public static UserFavoriteGetIsFavoriteRequestDto of(String userUuid, String parkingLotUuid) {
        return UserFavoriteGetIsFavoriteRequestDto.builder()
                .userUuid(userUuid)
                .parkingLotUuid(parkingLotUuid)
                .build();
    }

}
