package com.parkmate.userservice.kafka.producer;

import com.parkmate.userservice.kafka.event.ReviewCreatedJoinUserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewCreatedJoinUserProducer {

    private final KafkaTemplate<String, ReviewCreatedJoinUserEvent> kafkaTemplate;

    private static final String TOPIC = "user.review-join-user.created";

    public void send(ReviewCreatedJoinUserEvent event) {
        log.info("[Kafka Producer] Sending CreateReviewJoinUserEvent: {}", event);
        kafkaTemplate.send(TOPIC, event.getUserUuid(), event);
    }
}