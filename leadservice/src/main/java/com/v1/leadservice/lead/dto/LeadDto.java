package com.v1.leadservice.lead.dto;

import com.v1.leadservice.base.BaseDto;
import com.v1.leadservice.classes.User;
import com.v1.leadservice.utils.Constants.ErrorType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeadDto extends BaseDto {
    @Email(message = ErrorType.INVALID_EMAIL)
    private String email;

    @NotNull(message = ErrorType.FIELD_NULL)
    @NotBlank(message = ErrorType.FIELD_BLANK)
    private String phone;

    @NotNull(message = ErrorType.FIELD_NULL)
    @NotBlank(message = ErrorType.FIELD_BLANK)
    private String name;

    @NotNull(message = ErrorType.FIELD_NULL)
    @NotBlank(message = ErrorType.FIELD_BLANK)
    private String requirement;

    private User userCreate;
}
