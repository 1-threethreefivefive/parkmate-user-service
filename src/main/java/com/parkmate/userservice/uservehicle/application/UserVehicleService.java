package com.parkmate.userservice.uservehicle.application;

import com.parkmate.userservice.uservehicle.dto.request.UserVehicleDeleteRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleGetRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleRegisterRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleSetDefaultDto;
import com.parkmate.userservice.uservehicle.dto.response.UserVehicleGetAllResponseDto;
import com.parkmate.userservice.uservehicle.dto.response.UserVehicleGetResponseDto;

import java.util.List;

public interface UserVehicleService {

    void register(UserVehicleRegisterRequestDto userVehicleRegisterRequestDto);

    void delete(UserVehicleDeleteRequestDto userVehicleDeleteRequestDto);

    List<UserVehicleGetAllResponseDto> getAllVehicles(String userUuid);

    UserVehicleGetResponseDto getVehicle(UserVehicleGetRequestDto userVehicleGetRequestDto);

    void setDefault(UserVehicleSetDefaultDto userVehicleSetDefaultDto);

}
