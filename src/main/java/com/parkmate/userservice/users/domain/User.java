package com.parkmate.userservice.users.domain;

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
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 UUID")
    @Column(name = "user_uuid", nullable = false, length = 36)
    private String userUuid;

    @Comment("유저 이름")
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Comment("유저 전화번호")
    @Column(name = "phone_number", nullable = true, unique = true, length = 20)
    private String phoneNumber;

    @Comment("유저 포인트")
    @Column(name = "point", nullable = false)
    private int point = 0;

    @Comment("삭제 여부")
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Comment("삭제 일시")
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    private User(Long id, String userUuid, String name, String phoneNumber, int point, boolean isDeleted, LocalDateTime deletedAt) {
        this.id = id;
        this.userUuid = userUuid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public void update(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void delete(){
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public void restore() {
        this.isDeleted = false;
    }

}