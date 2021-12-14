package com.switchfully.codecoach.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class CoachFeedbackDTO {

    @Min(value = 0)
    @Max(value = 10)
    private int clarity;
    @Min(value = 0)
    @Max(value = 10)
    private int usefulness;
    @NotBlank(message = "positives can not be blank")
    @NotNull(message = "positives can not be null.")
    private String positives;
    @NotBlank(message = "workingPoints can not be blank")
    @NotNull(message = "workingPoints can not be null.")
    private String workingPoints;
}
