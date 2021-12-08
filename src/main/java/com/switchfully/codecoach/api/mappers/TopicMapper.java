package com.switchfully.codecoach.api.mappers;

import com.switchfully.codecoach.api.dto.TopicDTO;
import com.switchfully.codecoach.domain.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

    public Topic toEntity(TopicDTO topicDTO){
        return Topic.builder()
                .topicname((topicDTO.getTopicName()))
                .experience(topicDTO.getExperience())
                .build();
    }

    public TopicDTO toDTO(Topic topic){
        return TopicDTO.builder()
                .topicName(topic.getTopicname())
                .experience(topic.getExperience())
                .build();

    }
}
