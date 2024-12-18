package com.v1.tour.role.dto;

import com.v1.tour.base.BaseDto;
import com.v1.tour.enums.EnumRoleName;

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
public class RoleDto extends BaseDto {
    private EnumRoleName name;
}
