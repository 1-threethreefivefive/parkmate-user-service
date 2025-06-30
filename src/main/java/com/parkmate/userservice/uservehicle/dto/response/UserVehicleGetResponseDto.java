package com.parkmate.userservice.uservehicle.dto.response;

import com.parkmate.userservice.uservehicle.domain.UserVehicle;
import com.parkmate.userservice.uservehicle.vo.response.UserVehicleGetResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleGetResponseDto {

    private String vehicleNumber;
    private String nickname;
    private boolean defaultSelected;

    @Builder
    private UserVehicleGetResponseDto(String vehicleNumber,
                                      String nickname,
                                      boolean defaultSelected) {
        this.vehicleNumber = vehicleNumber;
        this.nickname = nickname;
        this.defaultSelected = defaultSelected;
    }

    public static UserVehicleGetResponseDto from(UserVehicle existingUserVehicle) {
        return UserVehicleGetResponseDto.builder()
                .vehicleNumber(existingUserVehicle.getVehicleNumber())
                .nickname(existingUserVehicle.getNickname())
                .defaultSelected(existingUserVehicle.isDefaultSelected())
                .build();
    }

    public UserVehicleGetResponseVo toVo() {
        return UserVehicleGetResponseVo.builder()
                .vehicleNumber(vehicleNumber)
                .nickname(nickname)
                .defaultSelected(defaultSelected)
                .build();
    }

}
