package com.parkmate.userservice.kafka.consumer;

import com.parkmate.userservice.common.exception.BaseException;
import com.parkmate.userservice.common.response.ResponseStatus;
import com.parkmate.userservice.kafka.event.ReviewCreatedEvent;
import com.parkmate.userservice.kafka.event.ReviewCreatedJoinUserEvent;
import com.parkmate.userservice.kafka.producer.ReviewCreatedJoinUserProducer;
import com.parkmate.userservice.users.domain.User;
import com.parkmate.userservice.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewCreatedEventConsumer {

    private final UserRepository userRepository;
    private final ReviewCreatedJoinUserProducer reviewCreatedJoinUserProducer;

    @KafkaListener(topics = "review.review.created", groupId = "create-review-join-user-group")
    public void consumeCreateReviewEvent(ReviewCreatedEvent event) {
        log.info("[Kafka] Consuming CreateReviewEvent: {}", event);

        User user = userRepository.findByUserUuid(event.getUserUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_FIND_USER));

        ReviewCreatedJoinUserEvent joinEvent = ReviewCreatedJoinUserEvent.builder()
                .reviewUuid(event.getReviewUuid())
                .userUuid(event.getUserUuid())
                .name(user.getName())
                .parkingLotUuid(event.getParkingLotUuid())
                .content(event.getContent())
                .imageUrls(event.getImageUrls())
                .rating(event.getRating())
                .createdAt(event.getCreatedAt())
                .build();

        reviewCreatedJoinUserProducer.send(joinEvent);
    }
}