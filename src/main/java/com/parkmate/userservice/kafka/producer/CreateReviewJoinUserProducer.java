package com.parkmate.userservice.kafka.producer;

import com.parkmate.userservice.kafka.event.CreateReviewJoinUserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateReviewJoinUserProducer {

    private final KafkaTemplate<String, CreateReviewJoinUserEvent> kafkaTemplate;

    private static final String TOPIC = "create-review-join-user";

    public void send(CreateReviewJoinUserEvent event) {
        log.info("[Kafka Producer] Sending CreateReviewJoinUserEvent: {}", event);
        kafkaTemplate.send(TOPIC, event.getUserUuid(), event);
    }
}