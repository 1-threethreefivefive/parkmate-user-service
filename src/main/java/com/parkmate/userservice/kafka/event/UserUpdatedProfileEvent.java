package com.parkmate.userservice.kafka.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserUpdatedProfileEvent {

    private String userUuid;
    private String name;
    private LocalDateTime timestamp;

    @Builder
    private UserUpdatedProfileEvent(String userUuid, String name) {
        this.userUuid = userUuid;
        this.name = name;
        this.timestamp = LocalDateTime.now();
    }
}