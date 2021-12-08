package com.switchfully.codecoach.api.dto;

public class CoachInfoDTO {


    @NotBlank(message = "Introduction cannot be blank") @NotNull(message="Introduction cannot be null")
    private String introduction;
    @NotBlank(message = "Availability cannot be blank") @NotNull(message="Availability cannot be null")
    private String availability;
    @Valid
    private List<TopicDTO> topics;
}
