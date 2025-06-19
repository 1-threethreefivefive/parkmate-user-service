package com.parkmate.userservice.userfavorite.domain;

import com.parkmate.userservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_favorite")
public class UserFavorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 UUID")
    @Column(name = "user_uuid", nullable = false, length = 36)
    private String userUuid;

    @Comment("주차장 UUID")
    @Column(name = "parking_lot_uuid", nullable = false, length = 36)
    private String parkingLotUuid;

    @Comment("삭제 여부")
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Comment("삭제 일시")
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    private UserFavorite(Long id, String userUuid, String parkingLotUuid, boolean isDeleted, LocalDateTime deletedAt) {
        this.id = id;
        this.userUuid = userUuid;
        this.parkingLotUuid = parkingLotUuid;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public void delete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public void restore() {
        this.isDeleted = false;
    }

}