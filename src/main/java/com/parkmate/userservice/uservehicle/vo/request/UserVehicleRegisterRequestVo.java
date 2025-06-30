package com.parkmate.userservice.uservehicle.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVehicleRegisterRequestVo {

    private String vehicleNumber;
    private String nickname;
    private boolean defaultSelected;

}
