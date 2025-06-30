package com.parkmate.userservice.uservehicle.application;

import com.parkmate.userservice.common.exception.BaseException;
import com.parkmate.userservice.common.response.ResponseStatus;
import com.parkmate.userservice.uservehicle.domain.UserVehicle;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleDeleteRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleGetRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleRegisterRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleSetDefaultDto;
import com.parkmate.userservice.uservehicle.dto.response.UserVehicleGetAllResponseDto;
import com.parkmate.userservice.uservehicle.dto.response.UserVehicleGetResponseDto;
import com.parkmate.userservice.uservehicle.infrastructure.UserVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserVehicleServiceImpl implements UserVehicleService {

    private final UserVehicleRepository userVehicleRepository;

    @Transactional
    @Override
    public void register(UserVehicleRegisterRequestDto userVehicleRegisterRequestDto) {

        if (userVehicleRegisterRequestDto.isDefaultSelected()) {
            Optional<UserVehicle> optionalUserVehicle = userVehicleRepository.findByUserUuidAndDefaultSelectedTrue(userVehicleRegisterRequestDto.getUserUuid());
            optionalUserVehicle.ifPresent(
                    existingUserVehicle -> {
                        existingUserVehicle.disableDefault();
                    }
            );
        }

        Optional<UserVehicle> optionalDeletedVehicle = userVehicleRepository.findByUserUuidAndVehicleNumberAndDeletedSelectedTrue(
                userVehicleRegisterRequestDto.getUserUuid(),
                userVehicleRegisterRequestDto.getVehicleNumber()
        );

        if (optionalDeletedVehicle.isPresent()) {
            UserVehicle deletedVehicle = optionalDeletedVehicle.get();
            deletedVehicle.restore(userVehicleRegisterRequestDto.getNickname(),
                    userVehicleRegisterRequestDto.isDefaultSelected());
            return;
        }

        userVehicleRepository.save(userVehicleRegisterRequestDto.toEntity());
    }

    @Transactional
    @Override
    public void delete(UserVehicleDeleteRequestDto userVehicleDeleteRequestDto) {

        Optional<UserVehicle> optionalUserVehicle = userVehicleRepository.findByUserUuidAndVehicleUuidAndDeletedSelectedFalse(userVehicleDeleteRequestDto.getUserUuid(),
                userVehicleDeleteRequestDto.getVehicleUuid());

        optionalUserVehicle.ifPresentOrElse(
                existingUserVehicle -> {
                    existingUserVehicle.enableDeleted();
                },
                () -> {
                    throw new BaseException(ResponseStatus.FAILED_TO_FIND_VEHICLE);
                }
        );
    }

    @Override
    public List<UserVehicleGetAllResponseDto> getAllVehicles(String userUuid) {
        List<UserVehicle> vehicles = userVehicleRepository.findAllByUserUuidAndDeletedSelectedFalse(userUuid);

        return vehicles.stream()
                .map(UserVehicleGetAllResponseDto::from)
                .toList();
    }

    @Override
    public UserVehicleGetResponseDto getVehicle(UserVehicleGetRequestDto userVehicleGetRequestDto) {
        Optional<UserVehicle> optionalUserVehicle = userVehicleRepository.findByUserUuidAndVehicleUuidAndDeletedSelectedFalse(userVehicleGetRequestDto.getUserUuid(),
                userVehicleGetRequestDto.getVehicleUuid());

        if (optionalUserVehicle.isPresent()) {
            UserVehicle existingUserVehicle = optionalUserVehicle.get();
            return UserVehicleGetResponseDto.from(existingUserVehicle);
        } else {
            throw new BaseException(ResponseStatus.FAILED_TO_FIND_VEHICLE);
        }

    }

    @Transactional
    @Override
    public void setDefault(UserVehicleSetDefaultDto userVehicleSetDefaultDto) {
        Optional<UserVehicle> optionalDefaultUserVehicle = userVehicleRepository.findByUserUuidAndDefaultSelectedTrue(userVehicleSetDefaultDto.getUserUuid());

        if (optionalDefaultUserVehicle.isPresent()) {
            UserVehicle existingUserVehicle = optionalDefaultUserVehicle.get();
            existingUserVehicle.disableDefault();
        }

        Optional<UserVehicle> optionalUserVehicle = userVehicleRepository.findByUserUuidAndVehicleUuidAndDeletedSelectedFalse(userVehicleSetDefaultDto.getUserUuid(),
                userVehicleSetDefaultDto.getVehicleUuid());

        optionalUserVehicle.ifPresentOrElse(
                existingUserVehicle -> {
                    existingUserVehicle.enableDefault();
                },
                () -> {
                    throw new BaseException(ResponseStatus.FAILED_TO_FIND_VEHICLE);
                }
        );
    }

}
