package com.switchfully.codecoach.api.dto;

import com.switchfully.codecoach.domain.Topic;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Builder
@Data
public class TopicDTO {

    private UUID id;
    private Topic.TopicName topicName;
    private int experience;
}
