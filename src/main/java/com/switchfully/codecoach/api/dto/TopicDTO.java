package com.switchfully.codecoach.api.dto;

import com.switchfully.codecoach.domain.Topic;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Builder
@Data
public class TopicDTO {


    private Topic.TopicName topicName;

    @Min(value=0) @Max(value=10)
    private int experience;
}
