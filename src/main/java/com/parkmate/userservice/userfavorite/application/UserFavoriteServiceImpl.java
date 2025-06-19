package com.parkmate.userservice.userfavorite.application;

import com.parkmate.userservice.common.exception.BaseException;
import com.parkmate.userservice.common.response.ResponseStatus;
import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteDeleteRequestDto;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteRegisterRequestDto;
import com.parkmate.userservice.userfavorite.dto.response.UserFavoriteResponseDto;
import com.parkmate.userservice.userfavorite.infrastructure.UserFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFavoriteServiceImpl implements UserFavoriteService {

    private final UserFavoriteRepository userFavoriteRepository;

    @Transactional
    @Override
    public void register(UserFavoriteRegisterRequestDto userFavoriteRegisterRequestDto) {
        Optional<UserFavorite> optionalExistingFavorite = userFavoriteRepository.findByUserUuidAndParkingLotUuid(
                userFavoriteRegisterRequestDto.getUserUuid(),
                userFavoriteRegisterRequestDto.getParkingLotUuid());

        optionalExistingFavorite.ifPresentOrElse(
                existingFavorite -> {
                    if (existingFavorite.isDeleted()) {
                        existingFavorite.restore();
                    } else {
                        throw new BaseException(ResponseStatus.ALREADY_EXIST_FAVORITE);
                    }
                },
                () -> userFavoriteRepository.save(userFavoriteRegisterRequestDto.toEntity())
        );

    }

    @Transactional
    @Override
    public void deleteFavorite(UserFavoriteDeleteRequestDto userFavoriteDeleteRequestDto) {
        UserFavorite userFavorite = userFavoriteRepository.findByUserUuidAndParkingLotUuid(
                        userFavoriteDeleteRequestDto.getUserUuid(),
                        userFavoriteDeleteRequestDto.getParkingLotUuid())

                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        userFavorite.delete();
    }

    @Override
    public List<UserFavoriteResponseDto> findParkingLotListByUserUuid(String userUuid) {

        return userFavoriteRepository.findAllByUserUuidAndIsDeletedFalse(userUuid)
                .stream()
                .map(UserFavoriteResponseDto::from)
                .toList();
    }

}
