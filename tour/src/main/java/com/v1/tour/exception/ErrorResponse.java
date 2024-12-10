package com.v1.tour.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponse {
    @Default
    private Boolean status = false;
    private String message;
    private String description;
    private String type;

    @Default
    private Long time = new Date().getTime();
}
