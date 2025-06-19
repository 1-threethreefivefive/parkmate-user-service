package com.parkmate.userservice.users.application;

import com.parkmate.userservice.users.dto.request.UserRegisterRequestDto;
import com.parkmate.userservice.users.dto.request.UserRegisterSocialRequestDto;
import com.parkmate.userservice.users.dto.request.UserUpdateRequestDto;
import com.parkmate.userservice.users.dto.response.UserGetNameResponseDto;
import com.parkmate.userservice.users.dto.response.UserGetPointResponseDto;
import com.parkmate.userservice.users.dto.response.UserGetResponseDto;

public interface UserService {

    void createUser(UserRegisterRequestDto userRegisterRequestDto);

    void createSocialUser(UserRegisterSocialRequestDto userRegisterSocialRequestDto);

    void updateUser(UserUpdateRequestDto userUpdateRequestDto);

    UserGetResponseDto findUserByUuid(String userUuid);

    void deleteUser(String userUuid);

    UserGetNameResponseDto findUserNameByUuid(String userUuid);

    UserGetPointResponseDto findUserPointByUuid(String userUuid);

}