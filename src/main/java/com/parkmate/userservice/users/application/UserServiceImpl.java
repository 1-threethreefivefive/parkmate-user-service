package com.parkmate.userservice.users.application;

import com.parkmate.userservice.common.exception.BaseException;
import com.parkmate.userservice.common.response.ResponseStatus;
import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.dto.request.UserRegisterRequestDto;
import com.parkmate.userservice.users.dto.request.UserUpdateRequestDto;
import com.parkmate.userservice.users.dto.response.UserGetNameResponseDto;
import com.parkmate.userservice.users.dto.response.UserGetPointResponseDto;
import com.parkmate.userservice.users.dto.response.UserGetResponseDto;
import com.parkmate.userservice.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(UserRegisterRequestDto userRegisterRequestDto) {
        Optional<User> isExist = userRepository.findByUserUuid(userRegisterRequestDto.getUserUuid());

        if (isExist.isPresent()) {
            User existingUser = isExist.get();

            if (existingUser.isDeleted()) {
                existingUser.restore();
                userRepository.save(existingUser);
            } else {
                throw new BaseException(ResponseStatus.ALREADY_EXIST_USER);
            }

        } else {
            userRepository.save(userRegisterRequestDto.toEntity());
        }
    }

    @Transactional
    @Override
    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findByUserUuid(userUpdateRequestDto.getUserUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        user.update(userUpdateRequestDto.getName(),
                userUpdateRequestDto.getPhoneNumber());
    }

    @Transactional(readOnly = true)
    @Override
    public UserGetResponseDto findUserByUuid(String userUuid) {
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        return UserGetResponseDto.from(user);
    }

    @Transactional
    @Override
    public void deleteUser(String userUuid) {
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        user.delete();
    }

    @Override
    public UserGetNameResponseDto findUserNameByUuid(String userUuid) {
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        return UserGetNameResponseDto.from(user);
    }

    @Override
    public UserGetPointResponseDto findUserPointByUuid(String userUuid) {
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        return UserGetPointResponseDto.from(user);
    }

}