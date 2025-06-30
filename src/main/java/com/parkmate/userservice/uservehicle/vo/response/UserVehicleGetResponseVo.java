package com.parkmate.userservice.uservehicle.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleGetResponseVo {

    private String vehicleNumber;
    private String nickname;
    private boolean defaultSelected;

    @Builder
    private UserVehicleGetResponseVo(String vehicleNumber,
                                     String nickname,
                                     boolean defaultSelected) {
        this.vehicleNumber = vehicleNumber;
        this.nickname = nickname;
        this.defaultSelected = defaultSelected;
    }

}
