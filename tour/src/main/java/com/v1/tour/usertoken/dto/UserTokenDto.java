package com.v1.tour.usertoken.dto;

import java.util.Date;
import java.util.UUID;

import com.v1.tour.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserTokenDto extends BaseDto {
    private UUID userId;
    private String accessToken;
    private UUID refreshToken;
    private Date refreshTokenExpire;
}
