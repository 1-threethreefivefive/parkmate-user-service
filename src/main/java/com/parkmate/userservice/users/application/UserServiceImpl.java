package com.parkmate.userservice.users.application;

import com.parkmate.userservice.common.exception.BaseException;
import com.parkmate.userservice.common.response.ResponseStatus;
import com.parkmate.userservice.kafka.event.UserUpdatedProfileEvent;
import com.parkmate.userservice.kafka.producer.UserUpdatedProfileProducer;
import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.dto.request.UserRegisterRequestDto;
import com.parkmate.userservice.users.dto.request.UserUpdateRequestDto;
import com.parkmate.userservice.users.dto.response.UserGetResponseDto;
import com.parkmate.userservice.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUpdatedProfileProducer userUpdatedProfileProducer;

    @Transactional
    @Override
    public void createUser(UserRegisterRequestDto userRegisterRequestDto) {
        userRepository.save(userRegisterRequestDto.toEntity());

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

    @Transactional
    @Override
    public void updateUserProfile(String userUuid, String name) {

        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus. FAILED_TO_FIND_USER));

        user.updateProfile(name);

        UserUpdatedProfileEvent event = UserUpdatedProfileEvent.builder()
                .userUuid(userUuid)
                .name(name)
                .build();

        userUpdatedProfileProducer.send(event);
    }
}