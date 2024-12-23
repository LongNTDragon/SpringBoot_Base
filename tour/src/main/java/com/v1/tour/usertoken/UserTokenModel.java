package com.v1.tour.usertoken;

import java.util.Date;
import java.util.UUID;

import com.v1.tour.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Table(name = "user_token")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserTokenModel extends BaseModel {
    private UUID userId;
    private String accessToken;
    private UUID refreshToken;
    private Date refreshTokenExpire;

    @Default
    private Boolean isRefreshed = false;
}
