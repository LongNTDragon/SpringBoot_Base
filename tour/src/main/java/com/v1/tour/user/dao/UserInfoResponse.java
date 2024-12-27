package com.v1.tour.user.dao;

import java.util.List;

import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.user.UserModel;

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
public class UserInfoResponse {
    private UserModel user;
    private List<EnumRoleName> roles;
}
