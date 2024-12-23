package com.v1.tour.auth.dto;

import com.v1.tour.utils.Constants.ErrorType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDto {
    @NotNull(message = ErrorType.FIELD_NULL)
    @Email(message = ErrorType.INVALID_EMAIL)
    @NotBlank(message = ErrorType.FIELD_BLANK)
    private String email;

    @NotNull(message = ErrorType.FIELD_NULL)
    private String password;
}
