package com.parkmate.userservice.kafka.producer;

import com.parkmate.userservice.kafka.event.UserUpdatedProfileEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUpdatedProfileProducer {

    private final KafkaTemplate<String, UserUpdatedProfileEvent> kafkaTemplate;

    private static final String TOPIC = "user.user-profile.updated";

    public void send(UserUpdatedProfileEvent event) {
        log.info("[Kafka Producer] Sending UpdateUserProfileEvent: {}", event);
        kafkaTemplate.send(TOPIC, event.getUserUuid(), event);
    }
}