package com.parkmate.userservice.userfavorite.application;

import com.parkmate.userservice.common.response.CursorPage;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteDeleteRequestDto;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteGetIsFavoriteRequestDto;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteGetRequestDto;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteRegisterRequestDto;
import com.parkmate.userservice.userfavorite.dto.response.UserFavoriteGetResponseDto;
import com.parkmate.userservice.userfavorite.dto.response.UserFavoriteResponseDto;

import java.util.List;

public interface UserFavoriteService {

    void register(UserFavoriteRegisterRequestDto userFavoriteRegisterRequestDto);

    void deleteFavorite(UserFavoriteDeleteRequestDto userFavoriteDeleteRequestDto);

    List<UserFavoriteResponseDto> findParkingLotListByUserUuid(String userUuid);

    CursorPage<UserFavoriteGetResponseDto> getAllFavorites(UserFavoriteGetRequestDto userFavoriteGetRequestDto);

    boolean isFavorite(UserFavoriteGetIsFavoriteRequestDto userFavoriteGetIsFavoriteRequestDto);

}
