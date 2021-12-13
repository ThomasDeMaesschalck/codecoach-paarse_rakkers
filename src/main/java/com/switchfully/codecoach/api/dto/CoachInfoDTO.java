package com.switchfully.codecoach.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
public class CoachInfoDTO {

    @NotNull(message="Introduction cannot be null")
    private String introduction;
    @NotNull(message="Availability cannot be null")
    private String availability;
    @Valid
    private List<TopicDTO> topics;
}
