package com.switchfully.codecoach.api.dto;

public class CoacheeFeedbackDTO {

    @Min(value = 0)
    @Max(value = 10)
    private int preparedness;
    @Min(value = 0)
    @Max(value = 10)
    private int willingness;
    @NotBlank(message = "positives can not be blank")
    @NotNull(message = "positives can not be null.")
    private String positives;
    @NotBlank(message = "Working Points can not be blank")
    @NotNull(message = "Working Points can not be null.")
    private String workingPoints;
}
