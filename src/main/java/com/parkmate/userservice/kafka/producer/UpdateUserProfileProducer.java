package com.parkmate.userservice.kafka.producer;

import com.parkmate.userservice.kafka.event.UpdateUserProfileEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserProfileProducer {

    private final KafkaTemplate<String, UpdateUserProfileEvent> kafkaTemplate;

    private static final String TOPIC = "update-user-profile";

    public void send(UpdateUserProfileEvent event) {
        log.info("[Kafka Producer] Sending UpdateUserProfileEvent: {}", event);
        kafkaTemplate.send(TOPIC, event.getUserUuid(), event);
    }
}