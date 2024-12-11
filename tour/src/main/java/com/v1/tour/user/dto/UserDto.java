package com.v1.tour.user.dto;

import com.v1.tour.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends BaseDto {
    private String username;
    private String email;
    private String phone;
    private String password;
}
