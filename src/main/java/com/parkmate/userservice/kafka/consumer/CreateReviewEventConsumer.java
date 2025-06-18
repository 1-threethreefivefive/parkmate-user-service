package com.parkmate.userservice.kafka.consumer;

import com.parkmate.userservice.kafka.event.CreateReviewEvent;
import com.parkmate.userservice.kafka.event.CreateReviewJoinUserEvent;
import com.parkmate.userservice.kafka.producer.CreateReviewJoinUserProducer;
import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateReviewEventConsumer {

    private final UserRepository userRepository;
    private final CreateReviewJoinUserProducer createReviewJoinUserProducer;

    @KafkaListener(topics = "create-review", groupId = "create-review-join-user-group")
    public void consumeCreateReviewEvent(CreateReviewEvent event) {
        log.info("[Kafka] Consuming CreateReviewEvent: {}", event);

        User user = userRepository.findByUserUuid(event.getUserUuid())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + event.getUserUuid()));

        CreateReviewJoinUserEvent joinEvent = CreateReviewJoinUserEvent.builder()
                .reviewUuid(event.getReviewUuid())
                .userUuid(event.getUserUuid())
                .name(user.getName())
                .parkingLotUuid(event.getParkingLotUuid())
                .content(event.getContent())
                .imageUrls(event.getImageUrls())
                .likeCount(event.getLikeCount())
                .dislikeCount(event.getDislikeCount())
                .rating(event.getRating())
                .createdAt(event.getCreatedAt())
                .build();

        createReviewJoinUserProducer.send(joinEvent);
    }
}