package com.v1.tour.role.dto;

import com.v1.tour.base.BaseDto;
import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.utils.Constants.ErrorType;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = ErrorType.FIELD_NULL)
    private EnumRoleName name;
}
