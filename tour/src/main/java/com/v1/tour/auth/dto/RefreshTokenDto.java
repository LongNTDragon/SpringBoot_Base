package com.v1.tour.auth.dto;

import java.util.UUID;

import com.v1.tour.utils.Constants.ErrorType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenDto {
    @NotNull(message = ErrorType.FIELD_NULL)
    private UUID refreshToken;
}
