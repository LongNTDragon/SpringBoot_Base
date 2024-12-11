package com.v1.tour.userrole.dto;

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
public class UserRoleDto extends BaseDto {
    private UUID userId;
    private UUID roleId;
}
