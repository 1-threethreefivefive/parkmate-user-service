package com.parkmate.userservice.userfavorite.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteDeleteRequestDto {

    private String userUuid;
    private String parkingLotUuid;

    @Builder
    private UserFavoriteDeleteRequestDto(String userUuid, String parkingLotUuid) {
        this.userUuid = userUuid;
        this.parkingLotUuid = parkingLotUuid;
    }

    public static UserFavoriteDeleteRequestDto from(String userUuid, String parkingLotUuid) {
        return UserFavoriteDeleteRequestDto.builder()
                .userUuid(userUuid)
                .parkingLotUuid(parkingLotUuid)
                .build();
    }

}
