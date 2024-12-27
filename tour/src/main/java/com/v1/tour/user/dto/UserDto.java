package com.v1.tour.user.dto;

import java.util.List;
import java.util.UUID;

import com.v1.tour.base.BaseDto;
import com.v1.tour.utils.Constants.ErrorType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = ErrorType.FIELD_NULL)
    @NotBlank(message = ErrorType.FIELD_BLANK)
    @Email(message = ErrorType.INVALID_EMAIL)
    private String email;
    @NotNull(message = ErrorType.FIELD_NULL)
    @NotBlank(message = ErrorType.FIELD_BLANK)
    private String password;
    @NotNull(message = ErrorType.FIELD_NULL)
    private List<UUID> roleIds;

    private String username;
    private String phone;
}
