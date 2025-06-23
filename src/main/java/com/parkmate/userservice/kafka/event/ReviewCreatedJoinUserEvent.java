package com.parkmate.userservice.kafka.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewCreatedJoinUserEvent {

    private String reviewUuid;
    private String userUuid;
    private String name;
    private String parkingLotUuid;
    private String content;
    private List<String> imageUrls;
    private int rating;
    private LocalDateTime createdAt;

    @Builder
    private ReviewCreatedJoinUserEvent(String reviewUuid,
                                       String userUuid,
                                       String name,
                                       String parkingLotUuid,
                                       String content,
                                       List<String> imageUrls,
                                       int rating,
                                       LocalDateTime createdAt) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
        this.name = name;
        this.parkingLotUuid = parkingLotUuid;
        this.content = content;
        this.imageUrls = imageUrls;
        this.rating = rating;
        this.createdAt = createdAt;
    }
}