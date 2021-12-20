package com.switchfully.codecoach.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private int xp;
}
