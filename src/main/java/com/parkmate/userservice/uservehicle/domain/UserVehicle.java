package com.parkmate.userservice.uservehicle.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parkmate.userservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVehicle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 차량 UUID")
    @Column(name = "vehicle_uuid", nullable = false, length = 36)
    private String vehicleUuid;

    @Comment("유저 UUID")
    @Column(name = "user_uuid", nullable = false, length = 36)
    private String userUuid;

    @Comment("차량 번호판")
    @Column(name = "vehicle_number", nullable = false, length = 50)
    private String vehicleNumber;

    @Comment("차량 닉네임")
    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    @Comment("기본 차량 여부")
    @Column(name = "default_selected", nullable = false)
    private boolean defaultSelected = false;

    @Comment("삭제 여부")
    @JsonProperty("isDeleted")
    @Column(name = "deleted_selected", nullable = false)
    private boolean deletedSelected = false;

    @Builder
    private UserVehicle(Long id,
                        String vehicleUuid,
                        String userUuid,
                        String vehicleNumber,
                        String nickname,
                        boolean defaultSelected,
                        boolean deletedSelected) {
        this.id = id;
        this.vehicleUuid = vehicleUuid;
        this.userUuid = userUuid;
        this.vehicleNumber = vehicleNumber;
        this.nickname = nickname;
        this.defaultSelected = defaultSelected;
        this.deletedSelected = deletedSelected;
    }

    public void disableDefault() {
        this.defaultSelected = false;
    }

    public void enableDefault() {
        this.defaultSelected = true;
    }

    public void enableDeleted() {
        this.deletedSelected = true;
    }

    public void restore(String nickname, boolean defaultSelected) {
        this.deletedSelected = false;
        this.nickname = nickname;
        this.defaultSelected = defaultSelected;
    }

}
