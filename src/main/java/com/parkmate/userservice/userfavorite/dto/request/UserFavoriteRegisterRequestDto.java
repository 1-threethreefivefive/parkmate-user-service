package com.parkmate.userservice.userfavorite.dto.request;

import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import com.parkmate.userservice.userfavorite.vo.request.UserFavoriteRegisterRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteRegisterRequestDto {

    private String userUuid;
    private String parkingLotUuid;

    @Builder
    private UserFavoriteRegisterRequestDto(String userUuid, String parkingLotUuid) {
        this.userUuid = userUuid;
        this.parkingLotUuid = parkingLotUuid;
    }

    public static UserFavoriteRegisterRequestDto of(String userUuid, UserFavoriteRegisterRequestVo userFavoriteRegisterRequestVo) {
        return UserFavoriteRegisterRequestDto.builder()
                .userUuid(userUuid)
                .parkingLotUuid(userFavoriteRegisterRequestVo.getParkingLotUuid())
                .build();
    }

    public UserFavorite toEntity() {
        return UserFavorite.builder()
                .userUuid(userUuid)
                .parkingLotUuid(parkingLotUuid)
                .build();
    }

}
