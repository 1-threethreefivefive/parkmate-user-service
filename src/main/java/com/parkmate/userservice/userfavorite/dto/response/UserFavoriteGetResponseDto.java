package com.parkmate.userservice.userfavorite.dto.response;

import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import com.parkmate.userservice.userfavorite.vo.response.UserFavoriteGetResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteGetResponseDto {

    private String parkingLotUuid;

    @Builder
    private UserFavoriteGetResponseDto(String parkingLotUuid) {
        this.parkingLotUuid = parkingLotUuid;
    }

    public static UserFavoriteGetResponseDto from(UserFavorite userFavorite) {
        return UserFavoriteGetResponseDto.builder()
                .parkingLotUuid(userFavorite.getParkingLotUuid())
                .build();
    }

    public UserFavoriteGetResponseVo toVo() {
        return UserFavoriteGetResponseVo.builder()
                .parkingLotUuid(parkingLotUuid)
                .build();
    }

}
